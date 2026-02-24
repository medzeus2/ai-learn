# 项目读取与运行结果

## 项目概览
- 项目类型：Spring Boot 3.5 + JDK 17 的人员信息维护示例。
- 启动命令：`mvn spring-boot:run`
- 关键访问地址：`http://localhost:8080/persons`、`http://localhost:8080/h2-console`

## 我执行的检查
1. `mvn test`
   - 结果：`BUILD SUCCESS`
   - 统计：`Tests run: 1, Failures: 0, Errors: 0, Skipped: 0`

2. 启动应用并请求页面
   - 命令流程：后台启动 `mvn spring-boot:run`，轮询 `http://localhost:8080/persons`
   - HTTP 状态码：`200`
   - 启动日志关键行：`Tomcat started on port 8080`、`Started PersonMaintenanceApplication`

## 页面返回片段（/persons）
返回 HTML 标题为“人员信息维护”，包含“新增人员”表单和“人员列表”表格，说明页面已成功渲染。
