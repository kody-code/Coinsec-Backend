CREATE TABLE IF NOT EXISTS account
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(50)    NOT NULL,
    type            VARCHAR(20)    NOT NULL, -- CASH/BANK/ALIPAY/WECHAT（代码枚举控制）
    initial_balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    currency        VARCHAR(10)             DEFAULT 'CNY',
    is_active       BOOLEAN                 DEFAULT true,
    created_at      TIMESTAMP               DEFAULT NOW(),
    updated_at      TIMESTAMP               DEFAULT NOW()
);
COMMENT ON table account IS '账户表';
COMMENT ON column account.id IS '账户ID';
COMMENT ON column account.name IS '账户名称';
COMMENT ON column account.type IS '账户类型';
COMMENT ON column account.initial_balance IS '账户初始余额';
COMMENT ON column account.currency IS '账户币种';
COMMENT ON column account.is_active IS '账户是否激活';
COMMENT ON column account.created_at IS '账户创建时间';
COMMENT ON column account.updated_at IS '账户更新时间';

CREATE TABLE IF NOT EXISTS category
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    parent_id  BIGINT REFERENCES category (id) ON DELETE RESTRICT, -- 禁止删除有子类的父类
    type       VARCHAR(10) NOT NULL,                               -- INCOME/EXPENSE（代码枚举控制）
    icon       VARCHAR(100),
    sort_order INT       DEFAULT 0,
    is_active  BOOLEAN   DEFAULT true,
    created_at TIMESTAMP DEFAULT NOW()
);
COMMENT ON table category IS '分类表';
COMMENT ON column category.id IS '分类ID';
COMMENT ON column category.name IS '分类名称';
COMMENT ON column category.parent_id IS '父分类ID';
COMMENT ON column category.type IS '分类类型';
COMMENT ON column category.icon IS '分类图标';
COMMENT ON column category.sort_order IS '分类排序顺序';
COMMENT ON column category.is_active IS '分类是否激活';
COMMENT ON column category.created_at IS '分类创建时间';

CREATE INDEX idx_category_parent_id ON category (parent_id);

CREATE TABLE IF NOT EXISTS transaction
(
    id            BIGSERIAL PRIMARY KEY,
    amount        DECIMAL(15, 2) NOT NULL CHECK (amount > 0),
    type          VARCHAR(10)    NOT NULL,                            -- INCOME/EXPENSE/TRANSFER（代码枚举控制）
    account_id    BIGINT         NOT NULL REFERENCES account (id) ON DELETE RESTRICT,
    to_account_id BIGINT REFERENCES account (id) ON DELETE RESTRICT,  -- 仅TRANSFER时非空
    category_id   BIGINT REFERENCES category (id) ON DELETE RESTRICT, -- TRANSFER时为NULL
    occurred_at   TIMESTAMP      NOT NULL DEFAULT NOW(),
    remark        VARCHAR(200),
    is_deleted    BOOLEAN                 DEFAULT false,
    created_at    TIMESTAMP               DEFAULT NOW()
);
COMMENT ON table transaction IS '交易表';
COMMENT ON column transaction.id IS '交易ID';
COMMENT ON column transaction.amount IS '交易金额';
COMMENT ON column transaction.type IS '交易类型';
COMMENT ON column transaction.account_id IS '账户ID';
COMMENT ON column transaction.to_account_id IS '对方账户ID';
COMMENT ON column transaction.category_id IS '分类ID';
COMMENT ON column transaction.occurred_at IS '发生时间';
COMMENT ON column transaction.remark IS '备注';
COMMENT ON column transaction.is_deleted IS '交易是否删除';
COMMENT ON column transaction.created_at IS '交易创建时间';

CREATE INDEX idx_trans_account_time
    ON transaction (account_id, occurred_at DESC)
    WHERE is_deleted = false;

CREATE INDEX idx_trans_toaccount_time
    ON transaction (to_account_id, occurred_at DESC)
    WHERE is_deleted = false AND to_account_id IS NOT NULL;

CREATE INDEX idx_trans_category
    ON transaction (category_id)
    WHERE is_deleted = false AND category_id IS NOT NULL;