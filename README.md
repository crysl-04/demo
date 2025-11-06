# Interest Circle 项目

## 项目简介

这是一个基于 Spring Boot 的兴趣圈子（Interest Circle）应用，提供了用户管理、帖子发布、圈子创建、音乐相关功能等核心功能。

## 技术栈

- **后端框架**: Spring Boot 3.3.1
- **Java 版本**: 17
- **数据库**: MySQL (通过 MyBatis 和 JPA)
- **前端**: HTML + CSS + JavaScript

## Git 远程仓库配置

本项目配置了两个远程仓库：

1. **GitHub (origin)**: `git@github.com:crysl-04/demo.git`
2. **Gitee (gitee)**: `https://gitee.com/crystalluo/interest-circle.git`

### 查看远程仓库

```bash
git remote -v
```

### 推送到不同的远程仓库

**推送到 GitHub:**
```bash
git push origin <分支名>
```

**推送到 Gitee:**
```bash
git push gitee <分支名>
```

**同时推送到两个仓库:**
```bash
git push origin <分支名> && git push gitee <分支名>
```

### 从不同的远程仓库拉取

**从 GitHub 拉取:**
```bash
git pull origin <分支名>
```

**从 Gitee 拉取:**
```bash
git pull gitee <分支名>
```

## 项目结构

```
demo/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── config/          # 配置类
│   │   │   ├── controller/      # 控制器层
│   │   │   ├── dto/             # 数据传输对象
│   │   │   ├── entity/          # 实体类
│   │   │   ├── exception/       # 异常处理
│   │   │   ├── mapper/          # MyBatis Mapper
│   │   │   └── service/         # 服务层
│   │   └── resources/
│   │       ├── static/          # 静态资源（CSS、JS、图片）
│   │       └── templates/       # Thymeleaf 模板
│   └── test/                    # 测试代码
├── pom.xml                      # Maven 配置文件
└── package.json                 # Node.js 依赖配置
```

## 主要功能模块

### 用户相关
- 用户注册和登录
- 用户信息管理
- 用户活动记录

### 兴趣圈子
- 创建和管理兴趣圈子
- 加入/退出圈子
- 圈子成员管理

### 帖子系统
- 发布帖子
- 帖子详情查看
- 帖子标签管理
- 按标签筛选帖子

### 音乐相关
- 歌曲管理
- 专辑管理
- 艺术家管理
- 罗马音转换

### 评论系统
- 帖子评论
- 评论管理

## 开发说明

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 5.7+ 或 8.0+
- Node.js (可选，用于前端依赖)

### 运行项目

1. 配置数据库连接（`src/main/resources/application.properties`）
2. 运行 Maven 构建：
   ```bash
   mvn clean install
   ```
3. 启动应用：
   ```bash
   mvn spring-boot:run
   ```
   或直接运行 `DemoApplication.java`

## 更新日志

### 2024-12-XX
- 添加 Gitee 远程仓库支持
- 配置双远程仓库（GitHub + Gitee）

