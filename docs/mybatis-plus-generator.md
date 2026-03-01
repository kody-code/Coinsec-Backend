# MyBatis-Plus 代码生成器配置文档

## 项目概述

本文档详细说明如何使用MyBatis-Plus Generator为CoinSec个人记账系统按表分模块生成代码。

## 技术栈确认

- **JDK版本**: 25
- **Spring Boot**: 3.5.11
- **MyBatis-Plus**: 3.5.15
- **数据库**: PostgreSQL
- **代码生成模板引擎**: FreeMarker 2.3.34

## 设计原则

遵循"避免过度设计"的原则，采用模块化代码生成策略：

1. 按数据库表分模块组织代码
2. 每个模块包含完整的MVC结构
3. 使用Lombok简化实体类
4. 保持模块间低耦合高内聚

## 模块化代码生成方案

### 模块划分原则

按照数据库表的业务关联性进行分组：

1. **用户模块** (user)
    - 表：users
    - 功能：用户注册、登录、个人信息管理

2. **账户模块** (account)
    - 表：accounts
    - 功能：账户创建、余额管理、账户信息维护

3. **分类模块** (category)
    - 表：categories
    - 功能：收支分类管理、分类树形结构

4. **交易模块** (transaction)
    - 表：transactions, transfers
    - 功能：交易记录、转账操作、交易查询

### 1. 模块化生成器配置类

```java
package com.kody.coinsec.backend.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * <p>
 * MyBatis-Plus 代码生成器配置
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
public class CodeGenerator {

    // 数据库配置
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USERNAME = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    // 包配置
    private static final String BASE_PACKAGE = "com.kody.coinsec.backend";
    private static final String MODULE_BASE_PATH = BASE_PACKAGE + ".module";

    public static void main(String[] args) {
        FastAutoGenerator.create(new DataSourceConfig.Builder(DB_URL, DB_USERNAME, DB_PASSWORD))
                // 全局配置
                .globalConfig(builder -> builder
                        .author("Kody")
                        .outputDir(System.getProperty("user.dir") + "/src/main/java")
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                )
                // 包配置 - 模块化结构
                .packageConfig(builder -> builder
                        .parent(MODULE_BASE_PATH)
                        .moduleName("user")
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .xml("mapper.xml")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.XML,
                                System.getProperty("user.dir") + "/src/main/resources/xml"))
                )
                // 针对不同模块的策略配置
                .strategyConfig(builder -> builder
                        .addInclude("users")
                        .entityBuilder()
                        .enableLombok()
                        .enableChainModel()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .addTableFills(
                                new Column("created_at", FieldFill.INSERT),
                                new Column("updated_at", FieldFill.INSERT_UPDATE)
                        )
                        .logicDeleteColumnName("deleted")
                        .idType(com.baomidou.mybatisplus.annotation.IdType.AUTO)
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .controllerBuilder()
                        .enableRestStyle()
                )
                // 策略配置
                .strategyConfig(builder -> builder
                        // 指定要生成的表
                        .addInclude("users", "accounts", "categories", "transactions", "transfers")

                        // 实体类策略
                        .entityBuilder()
                        .enableLombok()
                        .enableChainModel()
                        .enableRemoveIsPrefix()
                        .enableTableFieldAnnotation()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .addTableFills(
                                new Column("created_at", FieldFill.INSERT),
                                new Column("updated_at", FieldFill.INSERT_UPDATE)
                        )
                        .logicDeleteColumnName("deleted")
                        .idType(com.baomidou.mybatisplus.annotation.IdType.AUTO)

                        // Mapper策略
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .enableBaseColumnList()

                        // Service策略
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")

                        // Controller策略
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

        // 生成账户模块
        generateModule("account", "accounts");

        // 生成分类模块
        generateModule("category", "categories");

        // 生成交易模块
        generateModule("transaction", "transactions", "transfers");
    }

    /**
     * 生成单个模块
     */
    private static void generateModule(String moduleName, String... tables) {
        FastAutoGenerator.create(new DataSourceConfig.Builder(DB_URL, DB_USERNAME, DB_PASSWORD))
                .globalConfig(builder -> builder
                        .author("Kody")
                        .outputDir(System.getProperty("user.dir") + "/src/main/java")
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent(MODULE_BASE_PATH)
                        .moduleName(moduleName)
                        .entity("entity")
                        .service("service")
                        .serviceImpl("service.impl")
                        .mapper("mapper")
                        .xml("mapper.xml")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.XML,
                                System.getProperty("user.dir") + "/src/main/resources/xml"))
                )
                .strategyConfig(builder -> builder
                        .addInclude(tables)
                        .entityBuilder()
                        .enableLombok()
                        .enableChainModel()
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .addTableFills(
                                new Column("created_at", FieldFill.INSERT),
                                new Column("updated_at", FieldFill.INSERT_UPDATE)
                        )
                        .logicDeleteColumnName("deleted")
                        .idType(com.baomidou.mybatisplus.annotation.IdType.AUTO)
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .controllerBuilder()
                        .enableRestStyle()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
}
```

### 2. 自定义类型处理器

由于数据库使用了自定义枚举类型，需要创建对应的类型处理器：

```java
package com.kody.coinsec.backend.common.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * PostgreSQL枚举类型处理器基类
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
public abstract class BaseEnumTypeHandler<E extends Enum<E>> extends AbstractJsonTypeHandler<E> {

    private final Class<E> type;

    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    protected E parse(String json) {
        return json != null ? Enum.valueOf(type, json) : null;
    }

    @Override
    protected String toJson(E obj) {
        return obj != null ? obj.name() : null;
    }
}

/**
 * 交易类型枚举处理器
 */
@MappedTypes(TransactionTypeEnum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TransactionTypeEnumHandler extends BaseEnumTypeHandler<TransactionTypeEnum> {
    public TransactionTypeEnumHandler() {
        super(TransactionTypeEnum.class);
    }
}

/**
 * 账户类型枚举处理器
 */
@MappedTypes(AccountTypeEnum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class AccountTypeEnumHandler extends BaseEnumTypeHandler<AccountTypeEnum> {
    public AccountTypeEnumHandler() {
        super(AccountTypeEnum.class);
    }
}

/**
 * 分类类型枚举处理器
 */
@MappedTypes(CategoryTypeEnum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CategoryTypeEnumHandler extends BaseEnumTypeHandler<CategoryTypeEnum> {
    public CategoryTypeEnumHandler() {
        super(CategoryTypeEnum.class);
    }
}

/**
 * 货币类型枚举处理器
 */
@MappedTypes(CurrencyEnum.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class CurrencyEnumHandler extends BaseEnumTypeHandler<CurrencyEnum> {
    public CurrencyEnumHandler() {
        super(CurrencyEnum.class);
    }
}
```

### 3. 枚举类定义

```java
package com.kody.coinsec.backend.common.enums;

import lombok.Getter;

/**
 * <p>
 * 交易类型枚举
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
public enum TransactionTypeEnum {
    INCOME("income", "收入"),
    EXPENSE("expense", "支出"),
    TRANSFER("transfer", "转账");

    private final String code;
    private final String description;

    TransactionTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

/**
 * <p>
 * 账户类型枚举
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
public enum AccountTypeEnum {
    CASH("cash", "现金"),
    BANK_CARD("bank_card", "银行卡"),
    DIGITAL_WALLET("digital_wallet", "数字钱包"),
    CREDIT_CARD("credit_card", "信用卡"),
    INVESTMENT("investment", "投资账户"),
    OTHER("other", "其他");

    private final String code;
    private final String description;

    AccountTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

/**
 * <p>
 * 分类类型枚举
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
public enum CategoryTypeEnum {
    INCOME("income", "收入"),
    EXPENSE("expense", "支出");

    private final String code;
    private final String description;

    CategoryTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

/**
 * <p>
 * 货币类型枚举
 * </p>
 *
 * @author Kody
 * @since 2026-03-01
 */
@Getter
public enum CurrencyEnum {
    CNY("CNY", "人民币"),
    USD("USD", "美元"),
    EUR("EUR", "欧元"),
    JPY("JPY", "日元");

    private final String code;
    private final String description;

    CurrencyEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
```

## 模块化代码结构

运行代码生成器后，将按模块生成以下结构：

```
src/main/java/com/kody/coinsec/backend/
├── common/
│   ├── enums/
│   │   ├── TransactionTypeEnum.java
│   │   ├── AccountTypeEnum.java
│   │   ├── CategoryTypeEnum.java
│   │   └── CurrencyEnum.java
│   └── handler/
│       ├── BaseEnumTypeHandler.java
│       ├── TransactionTypeEnumHandler.java
│       ├── AccountTypeEnumHandler.java
│       ├── CategoryTypeEnumHandler.java
│       └── CurrencyEnumHandler.java
│
├── module/
│   ├── user/
│   │   ├── entity/User.java
│   │   ├── mapper/UserMapper.java
│   │   ├── service/UserService.java
│   │   ├── service/impl/UserServiceImpl.java
│   │   └── controller/UserController.java
│   │
│   ├── account/
│   │   ├── entity/Account.java
│   │   ├── mapper/AccountMapper.java
│   │   ├── service/AccountService.java
│   │   ├── service/impl/AccountServiceImpl.java
│   │   └── controller/AccountController.java
│   │
│   ├── category/
│   │   ├── entity/Category.java
│   │   ├── mapper/CategoryMapper.java
│   │   ├── service/CategoryService.java
│   │   ├── service/impl/CategoryServiceImpl.java
│   │   └── controller/CategoryController.java
│   │
│   └── transaction/
│       ├── entity/
│       │   ├── Transaction.java
│       │   └── Transfer.java
│       ├── mapper/
│       │   ├── TransactionMapper.java
│       │   └── TransferMapper.java
│       ├── service/
│       │   ├── TransactionService.java
│       │   └── TransferService.java
│       ├── service/impl/
│       │   ├── TransactionServiceImpl.java
│       │   └── TransferServiceImpl.java
│       └── controller/
│           ├── TransactionController.java
│           └── TransferController.java
│
└── CoinsecBackendApplication.java

src/main/resources/
└── xml/
    ├── UserMapper.xml
    ├── AccountMapper.xml
    ├── CategoryMapper.xml
    ├── TransactionMapper.xml
    └── TransferMapper.xml
```

## 模块对应关系

| 模块          | 数据库表                    | 主要实体                  | 核心功能 |
|-------------|-------------------------|-----------------------|------|
| user        | users                   | User                  | 用户管理 |
| account     | accounts                | Account               | 账户管理 |
| category    | categories              | Category              | 分类管理 |
| transaction | transactions, transfers | Transaction, Transfer | 交易管理 |

## 特殊处理说明

### 1. 数据库自增主键处理

所有实体类使用数据库自增作为主键，MyBatis-Plus配置为`AUTO`策略。

### 2. 时间字段自动填充

- `created_at`字段在插入时自动填充
- `updated_at`字段在插入和更新时自动填充

### 3. 逻辑删除支持

所有实体类支持逻辑删除，删除字段名为`deleted`。

### 4. 枚举类型映射

通过自定义TypeHandler处理PostgreSQL枚举类型到Java枚举的转换。

## 模块化生成步骤

1. **环境准备**
    - 确保数据库连接配置正确（通过环境变量）
    - 确认PostgreSQL数据库已创建并初始化

2. **运行生成器**
    - 执行`CodeGenerator.main()`方法
    - 系统将按顺序生成四个模块的代码

3. **代码验证**
    - 检查各模块代码的正确性
    - 验证实体类与数据库表的映射关系
    - 测试基础的CRUD功能

## 模块间依赖关系

```
common (公共组件)
├── enums (枚举类)
└── handler (类型处理器)

module (业务模块)
├── user (用户模块)
├── account (账户模块) 
├── category (分类模块)
└── transaction (交易模块)
    ├── 依赖 account 模块
    └── 依赖 category 模块
```

## 注意事项

1. 生成前请确保数据库表结构已正确创建
2. 生成的代码可能需要根据具体业务逻辑进行调整
3. 控制器层生成较为基础，建议根据RESTful规范进一步完善
4. 服务层会生成基础CRUD方法，复杂业务逻辑需要手动补充

## 模块化优势

1. **职责清晰** - 每个模块专注特定业务领域
2. **易于维护** - 修改某个模块不影响其他模块
3. **便于扩展** - 可以独立添加新的业务模块
4. **团队协作** - 不同开发者可以并行开发不同模块
5. **测试友好** - 可以针对单个模块进行单元测试

## 后续开发建议

1. **模块内部优化**
    - 在每个模块内添加DTO和VO对象
    - 实现模块内的业务逻辑校验
    - 添加模块专属的异常处理

2. **跨模块协调**
    - 定义模块间的服务调用接口
    - 实现分布式事务处理机制
    - 建立统一的数据传输规范

3. **系统集成**
    - 集成Redis缓存提升性能
    - 添加消息队列处理异步任务
    - 实现统一的日志收集和监控