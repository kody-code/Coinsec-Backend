# CoinSec 个人记账系统 API 接口设计文档

## 项目概述
为CoinSec个人记账系统设计完整的RESTful API接口，遵循"避免过度设计"原则，提供简洁实用的接口规范。

## 技术规范
- **协议**: HTTP/HTTPS
- **数据格式**: JSON
- **认证方式**: Sa-Token (通过Header传递token_name和token_value)
- **字符编码**: UTF-8
- **时间格式**: ISO 8601 (yyyy-MM-dd'T'HH:mm:ss.SSSZ)

## API 设计原则
1. **RESTful风格** - 使用标准HTTP方法(GET/POST/PUT/DELETE)
2. **语义化路由** - URL路径清晰表达资源含义
3. **统一响应格式** - 所有接口返回一致的数据结构
4. **合理的状态码** - 使用标准HTTP状态码
5. **版本控制** - API版本通过URL路径管理

## 统一响应格式

> 📋 详见 [统一响应格式规范](./response-format.md)

### 基础格式
```json
{
    "code": 200,
    "message": "操作成功",
    "data": {},
    "timestamp": "2026-03-01T16:45:30.123+08:00"
}
```

## 状态码规范

> 📋 详见 [统一响应格式规范](./response-format.md) 中的状态码规范

### 常用状态码
- **200**: 请求成功
- **201**: 创建成功  
- **400**: 请求参数错误
- **401**: 未授权/Token失效
- **403**: 权限不足
- **404**: 资源不存在
- **409**: 资源冲突
- **500**: 服务器内部错误

## 模块API设计

### 1. 用户管理模块 (User Module)

#### 1.1 用户注册
```
POST /api/v1/users/register
```

**请求参数:**
```json
{
    "username": "kody",
    "email": "kody@example.com",
    "password": "password123"
}
```

**响应示例:**
```json
{
    "code": 201,
    "message": "注册成功",
    "data": null,
    "timestamp": "2026-03-01T16:45:30.123+08:00"
}
```

#### 1.2 用户登录
```
POST /api/v1/users/login
```

**请求参数:**
```json
{
    "username": "kody",
    "password": "password123"
}
```

**响应示例:**
```json
{
    "code": 200,
    "message": "登录成功",
    "data": {
        "tokenValue": "ST-1234567890abcdef...",
        "userInfo": {
            "id": "550e8400-e29b-41d4-a716-446655440000",
            "username": "kody",
            "email": "kody@example.com"
        }
    },
    "timestamp": "2026-03-01T16:45:30.123+08:00"
}
```

#### 1.3 获取用户信息
```
GET /api/v1/users/profile
```

**Headers:**
```
Authorization: Bearer <token>
```

**响应示例:**
```json
{
    "code": 200,
    "message": "获取成功",
    "data": {
        "id": "550e8400-e29b-41d4-a716-446655440000",
        "username": "kody",
        "email": "kody@example.com",
        "lastLogin": "2026-03-01T16:45:30.123+08:00",
        "createdAt": "2026-03-01T16:45:30.123+08:00"
    },
    "timestamp": "2026-03-01T16:45:30.123+08:00"
}
```

#### 1.4 更新用户信息
```
PUT /api/v1/users/profile
```

**Headers:**
```
Authorization: Bearer <token>
```

**请求参数:**
```json
{
    "email": "newemail@example.com"
}
```

### 2. 账户管理模块 (Account Module)

#### 2.1 创建账户
```
POST /api/v1/accounts
```

**Headers:**
```
Authorization: Bearer <token>
```

**请求参数:**
```json
{
    "name": "支付宝-日常",
    "accountType": "digital_wallet",
    "balance": 1000.00,
    "currency": "CNY",
    "iconClass": "fa-alipay"
}
```

#### 2.2 查询账户列表
```
GET /api/v1/accounts
```

**Headers:**
```
Authorization: Bearer <token>
```

**查询参数:**
```
isActive=true&currency=CNY&page=1&size=10
```

#### 2.3 获取账户详情
```
GET /api/v1/accounts/{accountId}
```

#### 2.4 更新账户信息
```
PUT /api/v1/accounts/{accountId}
```

#### 2.5 删除账户
```
DELETE /api/v1/accounts/{accountId}
```

#### 2.6 账户余额调整
```
POST /api/v1/accounts/{accountId}/adjust-balance
```

**请求参数:**
```json
{
    "amount": 500.00,
    "type": "increase", // increase 或 decrease
    "description": "月初资金注入"
}
```

### 3. 分类管理模块 (Category Module)

#### 3.1 创建分类
```
POST /api/v1/categories
```

**请求参数:**
```json
{
    "name": "餐饮",
    "categoryType": "expense",
    "parentId": null,
    "iconClass": "fa-utensils",
    "colorHex": "#FF9800"
}
```

#### 3.2 查询分类列表
```
GET /api/v1/categories
```

**查询参数:**
```
categoryType=expense&isSystem=true
```

#### 3.3 获取分类树形结构
```
GET /api/v1/categories/tree
```

#### 3.4 更新分类
```
PUT /api/v1/categories/{categoryId}
```

#### 3.5 删除分类
```
DELETE /api/v1/categories/{categoryId}
```

### 4. 交易管理模块 (Transaction Module)

#### 4.1 记录收入
```
POST /api/v1/transactions/income
```

**请求参数:**
```json
{
    "accountId": "550e8400-e29b-41d4-a716-446655440000",
    "categoryId": "550e8400-e29b-41d4-a716-446655440001",
    "amount": 8000.00,
    "description": "工资收入",
    "transactionDate": "2026-03-01"
}
```

#### 4.2 记录支出
```
POST /api/v1/transactions/expense
```

**请求参数:**
```json
{
    "accountId": "550e8400-e29b-41d4-a716-446655440000",
    "categoryId": "550e8400-e29b-41d4-a716-446655440002",
    "amount": 150.00,
    "description": "午餐费用",
    "transactionDate": "2026-03-01"
}
```

#### 4.3 账户间转账
```
POST /api/v1/transactions/transfer
```

**请求参数:**
```json
{
    "fromAccountId": "550e8400-e29b-41d4-a716-446655440000",
    "toAccountId": "550e8400-e29b-41d4-a716-446655440001",
    "amount": 500.00,
    "description": "资金调配",
    "transactionDate": "2026-03-01"
}
```

#### 4.4 查询交易记录
```
GET /api/v1/transactions
```

**查询参数:**
```
accountId=xxx&type=expense&startDate=2026-03-01&endDate=2026-03-31&page=1&size=20
```

#### 4.5 获取交易详情
```
GET /api/v1/transactions/{transactionId}
```

#### 4.6 更新交易记录
```
PUT /api/v1/transactions/{transactionId}
```

#### 4.7 删除交易记录
```
DELETE /api/v1/transactions/{transactionId}
```

### 5. 统计报表模块 (Statistics Module)

#### 5.1 月度收支统计
```
GET /api/v1/statistics/monthly-summary
```

**查询参数:**
```
year=2026&month=3&currency=CNY
```

**响应示例:**
```json
{
    "code": 200,
    "message": "获取成功",
    "data": {
        "year": 2026,
        "month": 3,
        "currency": "CNY",
        "totalIncome": 8000.00,
        "totalExpense": 2500.00,
        "netIncome": 5500.00,
        "categoryStats": [
            {
                "categoryId": "550e8400-e29b-41d4-a716-446655440001",
                "categoryName": "餐饮",
                "amount": 800.00,
                "percentage": 32.0
            }
        ]
    },
    "timestamp": "2026-03-01T16:45:30.123+08:00"
}
```

#### 5.2 账户余额概览
```
GET /api/v1/statistics/account-balances
```

#### 5.3 分类支出排行
```
GET /api/v1/statistics/category-ranking
```

**查询参数:**
```
type=expense&period=month&limit=10
```

#### 5.4 年度趋势分析
```
GET /api/v1/statistics/yearly-trend
```

**查询参数:**
```
year=2026&currency=CNY
```

## 分页参数规范

### 请求参数
```
page=1      # 页码，从1开始
size=10     # 每页大小，默认10
sort=createdAt,desc  # 排序字段和方向
```

### 响应格式
```json
{
    "code": 200,
    "message": "获取成功",
    "data": {
        "content": [...],
        "pageable": {
            "pageNumber": 1,
            "pageSize": 10,
            "sort": {
                "sorted": true,
                "unsorted": false,
                "empty": false
            },
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "totalElements": 100,
        "totalPages": 10,
        "last": false,
        "size": 10,
        "number": 1,
        "first": true,
        "numberOfElements": 10,
        "empty": false
    },
    "timestamp": "2026-03-01T16:45:30.123+08:00"
}
```

## 错误处理规范

> 📋 详见 [统一响应格式规范](./response-format.md) 中的业务错误码规范

### 错误响应示例
```json
{
    "code": 400,
    "message": "请求参数错误",
    "data": null,
    "timestamp": "2026-03-01T16:45:30.123+08:00",
    "errors": [
        {
            "field": "username",
            "message": "用户名不能为空"
        }
    ]
}
```

## 安全考虑

1. **认证鉴权**: 所有接口都需要有效的Sa-Token
2. **权限控制**: 基于用户ID的数据隔离
3. **输入验证**: 严格的参数校验和过滤
4. **速率限制**: 防止恶意请求攻击
5. **敏感信息**: 密码等敏感信息不返回给客户端

## 性能优化建议

1. **缓存策略**: 常用分类、账户信息使用Redis缓存
2. **数据库索引**: 关键查询字段建立合适索引
3. **分页查询**: 大量数据采用分页处理
4. **懒加载**: 关联数据按需加载
5. **批量操作**: 支持批量导入导出功能

---
*文档版本: v1.0*
*最后更新: 2026-03-01*