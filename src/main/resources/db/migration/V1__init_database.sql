-- CoinSec 个人记账系统数据库初始化脚本
-- 基于稳定文档设计，适用于PostgreSQL数据库

-- 启用必需的扩展
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- 验证扩展是否成功加载
SELECT name, default_version, installed_version 
FROM pg_available_extensions 
WHERE name IN ('uuid-ossp', 'pgcrypto');

-- 创建自定义枚举类型
-- 交易类型枚举
CREATE TYPE transaction_type_enum AS ENUM ('income', 'expense', 'transfer');

-- 账户类型枚举
CREATE TYPE account_type_enum AS ENUM (
    'cash',              -- 现金
    'bank_card',         -- 银行卡
    'digital_wallet',    -- 数字钱包（包含支付宝、微信等）
    'credit_card',       -- 信用卡
    'investment',        -- 投资账户
    'other'              -- 其他
);

-- 分类类型枚举
CREATE TYPE category_type_enum AS ENUM ('income', 'expense');

-- 货币类型枚举
CREATE TYPE currency_enum AS ENUM ('CNY', 'USD', 'EUR', 'JPY');

-- ==================== 核心业务表 ====================

-- 1. 用户表 (users)
-- 存储用户基本信息和认证相关数据
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE,
    password_hash VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP WITH TIME ZONE
);

-- 添加表注释
COMMENT ON TABLE users IS '用户信息表，存储系统用户的基本信息和认证数据';
COMMENT ON COLUMN users.id IS '用户唯一标识符，使用UUID';
COMMENT ON COLUMN users.username IS '用户名，用于登录，唯一';
COMMENT ON COLUMN users.email IS '用户邮箱，可选，唯一';
COMMENT ON COLUMN users.password_hash IS '加密后的用户密码';
COMMENT ON COLUMN users.created_at IS '用户创建时间';
COMMENT ON COLUMN users.updated_at IS '用户信息最后更新时间';
COMMENT ON COLUMN users.last_login IS '用户最后登录时间';

-- 2. 账户表 (accounts)
-- 存储用户的各个账户信息
CREATE TABLE accounts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    account_type account_type_enum NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    currency currency_enum DEFAULT 'CNY',
    icon_class VARCHAR(50), -- Font Awesome图标类名
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 账户索引
CREATE INDEX idx_accounts_user_id ON accounts(user_id);

-- 添加表注释
COMMENT ON TABLE accounts IS '账户信息表，存储用户的各种财务账户';
COMMENT ON COLUMN accounts.id IS '账户唯一标识符，使用UUID';
COMMENT ON COLUMN accounts.user_id IS '所属用户ID，外键关联users表';
COMMENT ON COLUMN accounts.name IS '账户名称，如"支付宝-日常"、"现金钱包"等';
COMMENT ON COLUMN accounts.account_type IS '账户类型：现金、银行卡、数字钱包等';
COMMENT ON COLUMN accounts.balance IS '账户当前余额';
COMMENT ON COLUMN accounts.currency IS '账户货币类型，默认人民币';
COMMENT ON COLUMN accounts.icon_class IS 'Font Awesome图标类名，用于前端显示';
COMMENT ON COLUMN accounts.is_active IS '账户是否激活状态';
COMMENT ON COLUMN accounts.created_at IS '账户创建时间';
COMMENT ON COLUMN accounts.updated_at IS '账户信息最后更新时间';

-- 3. 交易分类表 (categories)
-- 存储系统预设的交易分类
CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL,
    category_type category_type_enum NOT NULL,
    parent_id UUID REFERENCES categories(id),
    icon_class VARCHAR(50), -- Font Awesome图标类名
    color_hex VARCHAR(7), -- 分类颜色 #RRGGBB
    sort_order INTEGER DEFAULT 0,
    is_system BOOLEAN DEFAULT true, -- 系统预设分类
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 添加表注释
COMMENT ON TABLE categories IS '交易分类表，存储系统预设的收支分类';
COMMENT ON COLUMN categories.id IS '分类唯一标识符，使用UUID';
COMMENT ON COLUMN categories.name IS '分类名称，如"工资"、"餐饮"等';
COMMENT ON COLUMN categories.category_type IS '分类类型：收入(income)或支出(expense)';
COMMENT ON COLUMN categories.parent_id IS '父分类ID，用于构建分类层级';
COMMENT ON COLUMN categories.icon_class IS 'Font Awesome图标类名';
COMMENT ON COLUMN categories.color_hex IS '分类显示颜色，十六进制格式';
COMMENT ON COLUMN categories.sort_order IS '分类排序序号';
COMMENT ON COLUMN categories.is_system IS '是否为系统预设分类';
COMMENT ON COLUMN categories.created_at IS '分类创建时间';

-- 4. 交易记录表 (transactions)
-- 存储具体的交易记录
CREATE TABLE transactions (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    account_id UUID NOT NULL REFERENCES accounts(id) ON DELETE CASCADE,
    category_id UUID REFERENCES categories(id),
    transaction_type transaction_type_enum NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    description TEXT,
    transaction_date DATE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 交易记录索引
CREATE INDEX idx_transactions_user_id ON transactions(user_id);
CREATE INDEX idx_transactions_account_id ON transactions(account_id);
CREATE INDEX idx_transactions_date ON transactions(transaction_date);

-- 添加表注释
COMMENT ON TABLE transactions IS '交易记录表，存储用户的所有收支交易';
COMMENT ON COLUMN transactions.id IS '交易唯一标识符，使用UUID';
COMMENT ON COLUMN transactions.user_id IS '所属用户ID，外键关联users表';
COMMENT ON COLUMN transactions.account_id IS '相关账户ID，外键关联accounts表';
COMMENT ON COLUMN transactions.category_id IS '交易分类ID，外键关联categories表';
COMMENT ON COLUMN transactions.transaction_type IS '交易类型：收入、支出、转账';
COMMENT ON COLUMN transactions.amount IS '交易金额';
COMMENT ON COLUMN transactions.description IS '交易描述说明';
COMMENT ON COLUMN transactions.transaction_date IS '交易发生日期';
COMMENT ON COLUMN transactions.created_at IS '交易记录创建时间';
COMMENT ON COLUMN transactions.updated_at IS '交易记录最后更新时间';

-- 5. 转账记录表 (transfers)
-- 专门记录账户间转账的关联信息
CREATE TABLE transfers (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    transaction_id UUID NOT NULL REFERENCES transactions(id) ON DELETE CASCADE,
    from_account_id UUID NOT NULL REFERENCES accounts(id) ON DELETE CASCADE,
    to_account_id UUID NOT NULL REFERENCES accounts(id) ON DELETE CASCADE,
    transfer_amount DECIMAL(15,2) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- 转账记录索引
CREATE INDEX idx_transfers_transaction_id ON transfers(transaction_id);

-- 添加表注释
COMMENT ON TABLE transfers IS '转账记录表，存储账户间的转账详情';
COMMENT ON COLUMN transfers.id IS '转账记录唯一标识符，使用UUID';
COMMENT ON COLUMN transfers.transaction_id IS '关联的交易记录ID';
COMMENT ON COLUMN transfers.from_account_id IS '转出账户ID';
COMMENT ON COLUMN transfers.to_account_id IS '转入账户ID';
COMMENT ON COLUMN transfers.transfer_amount IS '转账金额';
COMMENT ON COLUMN transfers.created_at IS '转账记录创建时间';

-- ==================== 约束定义 ====================

-- 唯一性约束
ALTER TABLE users ADD CONSTRAINT uk_users_username UNIQUE (username);
ALTER TABLE users ADD CONSTRAINT uk_users_email UNIQUE (email);

-- 检查约束
ALTER TABLE transactions ADD CONSTRAINT chk_transactions_amount_positive CHECK (amount > 0);
ALTER TABLE transfers ADD CONSTRAINT chk_transfers_amount_positive CHECK (transfer_amount > 0);
ALTER TABLE transfers ADD CONSTRAINT chk_transfers_different_accounts CHECK (from_account_id != to_account_id);

-- 业务约束：同一用户下账户名称唯一
CREATE UNIQUE INDEX uk_accounts_user_name ON accounts(user_id, name);

-- ==================== 初始化数据 ====================

-- 插入系统预设分类数据（使用Font Awesome图标）
INSERT INTO categories (name, category_type, icon_class, color_hex, sort_order, is_system) VALUES
-- 收入分类
('工资', 'income', 'fa-money-bill-wave', '#4CAF50', 1, true),
('奖金', 'income', 'fa-gift', '#8BC34A', 2, true),
('投资收益', 'income', 'fa-chart-line', '#FFC107', 3, true),
('其他收入', 'income', 'fa-coins', '#2196F3', 4, true),

-- 支出分类
('餐饮', 'expense', 'fa-utensils', '#FF9800', 1, true),
('交通', 'expense', 'fa-subway', '#2196F3', 2, true),
('购物', 'expense', 'fa-shopping-cart', '#E91E63', 3, true),
('娱乐', 'expense', 'fa-gamepad', '#9C27B0', 4, true),
('医疗', 'expense', 'fa-heartbeat', '#F44336', 5, true),
('住房', 'expense', 'fa-home', '#795548', 6, true),
('通讯', 'expense', 'fa-phone', '#607D8B', 7, true),
('教育', 'expense', 'fa-graduation-cap', '#3F51B5', 8, true),
('其他支出', 'expense', 'fa-question-circle', '#9E9E9E', 9, true);

-- ==================== 创建视图 ====================

-- 创建账户余额视图（用于查询实时余额）
CREATE OR REPLACE VIEW account_balances AS
SELECT 
    a.id,
    a.user_id,
    a.name,
    a.account_type,
    a.currency,
    a.balance as snapshot_balance,
    COALESCE(SUM(
        CASE 
            WHEN t.transaction_type = 'income' THEN t.amount
            WHEN t.transaction_type = 'expense' THEN -t.amount
            ELSE 0
        END
    ), 0) as calculated_balance,
    a.created_at,
    a.updated_at
FROM accounts a
LEFT JOIN transactions t ON a.id = t.account_id
GROUP BY a.id, a.user_id, a.name, a.account_type, a.currency, a.balance, a.created_at, a.updated_at;

-- 创建月度统计视图
CREATE OR REPLACE VIEW monthly_summary AS
SELECT 
    t.user_id,
    DATE_TRUNC('month', t.transaction_date) as month,
    a.currency,
    SUM(CASE WHEN t.transaction_type = 'income' THEN t.amount ELSE 0 END) as total_income,
    SUM(CASE WHEN t.transaction_type = 'expense' THEN t.amount ELSE 0 END) as total_expense,
    SUM(CASE WHEN t.transaction_type = 'income' THEN t.amount ELSE 0 END) - 
    SUM(CASE WHEN t.transaction_type = 'expense' THEN t.amount ELSE 0 END) as net_income
FROM transactions t
JOIN accounts a ON t.account_id = a.id
GROUP BY t.user_id, DATE_TRUNC('month', t.transaction_date), a.currency
ORDER BY month DESC;

-- ==================== 创建函数 ====================

-- 更新账户余额的函数
CREATE OR REPLACE FUNCTION update_account_balance(p_account_id UUID)
RETURNS VOID AS $$
DECLARE
    calculated_balance DECIMAL(15,2);
BEGIN
    SELECT COALESCE(SUM(
        CASE 
            WHEN t.transaction_type = 'income' THEN t.amount
            WHEN t.transaction_type = 'expense' THEN -t.amount
            ELSE 0
        END
    ), 0) INTO calculated_balance
    FROM transactions t
    WHERE t.account_id = p_account_id;
    
    UPDATE accounts 
    SET balance = calculated_balance,
        updated_at = CURRENT_TIMESTAMP
    WHERE id = p_account_id;
END;
$$ LANGUAGE plpgsql;

-- ==================== 创建触发器 ====================

-- 交易记录更新后自动更新账户余额
CREATE OR REPLACE FUNCTION trigger_update_balance()
RETURNS TRIGGER AS $$
BEGIN
    -- 更新相关账户余额
    IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE' THEN
        PERFORM update_account_balance(NEW.account_id::UUID);
    END IF;
    
    IF TG_OP = 'DELETE' THEN
        PERFORM update_account_balance(OLD.account_id::UUID);
    END IF;
    
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- 为交易表创建触发器
CREATE TRIGGER trg_transactions_balance_update
    AFTER INSERT OR UPDATE OR DELETE ON transactions
    FOR EACH ROW EXECUTE FUNCTION trigger_update_balance();

-- ==================== 权限设置 ====================

-- 创建应用用户角色（如果需要）
-- CREATE ROLE coinsec_app LOGIN PASSWORD 'your_secure_password';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO coinsec_app;
-- GRANT USAGE ON ALL SEQUENCES IN SCHEMA public TO coinsec_app;

-- ==================== 数据库维护 ====================

-- 创建定期清理过期数据的函数
CREATE OR REPLACE FUNCTION cleanup_old_data(days_to_keep INTEGER DEFAULT 365)
RETURNS INTEGER AS $$
DECLARE
    deleted_count INTEGER;
BEGIN
    -- 删除超过指定天数的旧日志或审计数据（如果有相关表的话）
    -- 这里可以根据实际需要添加具体的清理逻辑
    
    RAISE NOTICE '清理超过 % 天的旧数据', days_to_keep;
    RETURN 0;
END;
$$ LANGUAGE plpgsql;

-- ==================== 验证脚本 ====================

-- 验证数据库结构
SELECT 
    table_name,
    column_name,
    data_type,
    is_nullable,
    column_default
FROM information_schema.columns 
WHERE table_schema = 'public' 
    AND table_name IN ('users', 'accounts', 'categories', 'transactions', 'transfers')
ORDER BY table_name, ordinal_position;

-- 验证约束
SELECT 
    tc.table_name,
    tc.constraint_name,
    tc.constraint_type
FROM information_schema.table_constraints tc
WHERE tc.table_schema = 'public'
    AND tc.table_name IN ('users', 'accounts', 'categories', 'transactions', 'transfers')
ORDER BY tc.table_name, tc.constraint_name;

-- 显示初始化完成信息
SELECT '数据库初始化完成' as status_message;