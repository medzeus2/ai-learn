# ai-learn

Spring Boot 3.5 + JDK 17 的人员信息维护示例项目。

## 技术栈
- Spring Boot 3.5.0
- JDK 17
- Thymeleaf（模板引擎）
- Spring Data JPA
- H2 本地文件数据库
- Bootstrap 5

## 运行
```bash
mvn spring-boot:run
```

访问：
- 人员维护页面：`http://localhost:8080/persons`
- H2 控制台：`http://localhost:8080/h2-console`

## 表结构
`t_person` 字段如下：
- `id`
- `name`（姓名）
- `gender`（性别）
- `age`（年龄）
- `id_card`（身份证号）
- `status`（启用/禁用）
