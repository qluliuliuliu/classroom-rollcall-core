# 课堂点名系统 - 核心点名模块

## 模块说明

本模块负责基于有状态的课堂点名系统的核心点名逻辑，包括加权随机选择算法、救场机制、继续提问流程和主窗口架构。

## 文件说明

| 文件 | 说明 |
|------|------|
| `model/Student.java` | 学生实体模型（权重计算、序列化） |
| `model/RollCallRecord.java` | 点名记录模型 |
| `service/RollCallService.java` | 核心点名服务（状态管理、救场机制） |
| `util/WeightedRandom.java` | 加权随机算法（累积权重法） |
| `view/MainFrame.java` | 主窗口（JTabbedPane组织三面板） |
| `view/RollCallPanel.java` | 点名交互界面 |

## 核心算法

### 加权随机选择

权重 = 100 / (被点名次数 + 1)，被点名越少权重越高。使用累积权重法随机选取。

### 救场机制

连续3人未答出 → 自动从高回答率学生中选取 → 救场3连败 → 提示"题目太难，建议老师讲解"。

## 设计模式

- MVC三层架构（Model-View-Service）
- 策略模式（WeightedRandom为算法策略）

## 编译运行

依赖 `StudentDAO`、`ExcelUtil`（由其他模块提供），完整项目需合入主仓库编译。

```bash
javac -encoding UTF-8 -d out src/rollcall/**/*.java
```
