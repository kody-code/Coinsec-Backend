# CoinSec Backend

个人记账系统后端服务

## 技术栈

- JDK 25
- Spring Boot 3.5.11
- MyBatis-Plus 3.5.15
- PostgreSQL
- Redis (Valkey)

## 项目结构

```
src/main/java/com/kody/coinsec/backend/
├── common/              # 公共组件
│   ├── enums/          # 枚举类
│   ├── handler/        # 类型处理器
│   └── response/       # 统一响应格式
├── config/             # 配置类
├── generator/          # 代码生成器
├── module/             # 业务模块
│   ├── user/          # 用户模块
│   ├── account/       # 账户模块
│   ├── category/      # 分类模块
│   ├── transaction/   # 交易模块
│   └── statistics/    # 统计模块
└── test/              # 测试代码
```

## 开发环境配置

### 环境变量

```bash
export DB_URL=jdbc:postgresql://localhost:5432/coinsec
export DB_USER=your_username
export DB_PASSWORD=your_password
```

### 启动应用

```bash
# 编译项目
mvn clean compile

# 运行应用
mvn spring-boot:run

# 运行测试
mvn test
```

## GitHub Actions

### CI/CD 流程

1. **持续集成 (CI)**
    - 代码推送到 `main` 或 `develop` 分支时自动触发
    - 执行编译、测试和打包
    - 上传构建产物作为artifact

2. **自动发布 (Release)**
    - 当推送标签 `v*` 格式时自动触发
    - 自动构建并创建GitHub Release
    - 上传可执行JAR文件到Release

### 使用方法

#### 创建新版本发布

```bash
# 1. 更新版本号
# 修改 pom.xml 中的 <revision> 版本号

# 2. 提交更改
git add .
git commit -m "Prepare release vx.x.x"

# 3. 创建并推送标签
git tag v1.0.0
git push origin v1.0.0
```

#### 触发CI构建

```bash
# 推送代码到主分支
git push origin main

# 或创建Pull Request
```

## API文档

API接口使用统一的响应格式：

### 成功响应

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {},
  "timestamp": "2026-03-01 16:45:30"
}
```

### 错误响应

```json
{
  "code": 400,
  "message": "请求参数错误",
  "data": null,
  "timestamp": "2026-03-01 16:45:30",
  "errors": [
    {
      "field": "username",
      "message": "用户名不能为空"
    }
  ]
}
```

## 模块说明

### 用户模块

- 用户注册、登录
- 个人信息管理

### 账户模块

- 账户创建和管理
- 余额调整功能

### 分类模块

- 收支分类管理
- 分类树形结构

### 交易模块

- 收入支出记录
- 账户间转账

### 统计模块

- 月度收支统计
- 分类排行分析
- 趋势图表数据

## 开发规范

1. **代码风格**: 遵循Google Java Style Guide
2. **提交信息**: 使用 conventional commits 格式
3. **分支策略**: Git Flow 工作流
4. **测试覆盖**: 新功能需包含单元测试

## 许可证

MIT License