# Shape Shifter Curse — 项目结构与实现说明

## 基本信息

| 项目 | 详情 |
|------|------|
| **模组名称** | Shape Shifter Curse |
| **Mod ID** | `shape-shifter-curse` |
| **Minecraft 版本** | 1.20.1 |
| **模组加载器** | Fabric Loader >= 0.12.3 |
| **主包路径** | `net.onixary.shapeShifterCurseFabric` |
| **Java 版本** | Java 17 |
| **构建工具** | Gradle + Fabric Loom 1.9 |
| **模组版本** | 1.9.0 |
| **许可协议** | MIT |
| **作者** | onixary, XuHaoNan |

## 模组简介

Shape Shifter Curse 是一个以**逐渐变身为不同生物形态**为核心的模组。玩家被诅咒后会经历逐步的形态变化，每种形态基于原版生物，赋予玩家独特的能力与缺陷。模组内置了完整的起源(Origins)系统分支，并结合 Apoli 能力系统实现所有形态技能。

---

## 项目目录总览

```
shape-shifter-curse-fabric/
├── build.gradle              # Gradle 构建脚本
├── gradle.properties         # 版本依赖配置
├── settings.gradle           # Gradle 设置
├── src/main/
│   ├── java/net/onixary/shapeShifterCurseFabric/
│   │   ├── ShapeShifterCurseFabric.java          # 主模组入口 (ModInitializer)
│   │   ├── accessors/                            # 访问器接口
│   │   ├── additional_power/   (89 files)        # Apoli 扩展能力系统
│   │   ├── advancement/        (19 files)        # 自定义进度触发器
│   │   ├── blocks/             (4 files)         # 自定义方块
│   │   ├── client/             (2 files)         # 客户端入口
│   │   ├── command/            (3 files)         # 命令与参数类型
│   │   ├── config/             (5 files)         # 配置系统
│   │   ├── cursed_moon/        (1 file)          # 诅咒之月系统
│   │   ├── custom_ui/          (11 files)        # 自定义UI屏幕
│   │   ├── data/               (2 files)         # 静态参数/数据定义
│   │   ├── entity/             (3 files)         # 自定义实体
│   │   ├── features/           (3 files)         # 世界特征
│   │   ├── form_giving_custom_entity/ (13 files) # 给予形态的特殊实体
│   │   │   ├── axolotl/                          # 变形美西螈
│   │   │   ├── bat/                              # 变形蝙蝠
│   │   │   ├── ocelot/                           # 变形豹猫
│   │   │   ├── spider/                           # 变形蜘蛛
│   │   │   └── wolf/                             # 变形狼
│   │   ├── integration/       (71 files)         # 第三方模组集成
│   │   │   └── origins/                          # 内置 Origins 起源系统
│   │   │       ├── badge/                        # 徽章系统
│   │   │       ├── command/                      # 起源命令
│   │   │       ├── component/                    # CCA 组件
│   │   │       ├── content/                      # 起源物品/方块
│   │   │       ├── data/                         # 数据类型
│   │   │       ├── enchantment/                  # 附魔
│   │   │       ├── entity/                       # 起源实体(末影珍珠等)
│   │   │       ├── integration/                  # 集成回调
│   │   │       ├── mixin/                        # 起源相关 Mixin
│   │   │       ├── networking/                   # 网络通信
│   │   │       ├── origin/                       # 起源核心(层、管理、升级)
│   │   │       ├── power/                        # 起源能力类型
│   │   │       ├── registry/                     # 起源注册
│   │   │       ├── screen/                       # 起源选择UI
│   │   │       └── util/                         # 工具类
│   │   ├── items/             (50 files)         # 自定义物品
│   │   │   ├── accessory/                        # 饰品
│   │   │   ├── armors/                           # 护甲(变形鳞甲)
│   │   │   ├── tools/                            # 工具
│   │   │   └── trinkets/                         # Trinkets 饰品
│   │   ├── mana/              (10 files)         # 法力系统
│   │   ├── minion/            (12 files)         # 召唤物系统
│   │   │   └── mobs/                             # 召唤物实体
│   │   ├── misc/              (N/A)              # 杂项
│   │   ├── mixin/             (96 files)         # Mixin 注入
│   │   │   ├── accessor/                         # 访问器 Mixin
│   │   │   ├── accessory/                        # 饰品 Mixin
│   │   │   ├── block/                            # 方块 Mixin
│   │   │   ├── compatibility/                    # 兼容 Mixin (第一人称模型等)
│   │   │   ├── integration/                      # 集成 Mixin
│   │   │   ├── mob/                              # 生物行为 Mixin
│   │   │   ├── plugin/                           # Mixin 插件
│   │   │   ├── projectile/                       # 弹射物 Mixin
│   │   │   └── test/                             # 测试 Mixin
│   │   ├── networking/        (4 files)          # 网络数据包系统
│   │   ├── player_animation/  (29 files)         # 玩家动画系统
│   │   │   ├── form_animation/                   # 形态变换动画
│   │   │   ├── v2/                               # 动画系统 v2
│   │   │   └── v3/                               # 动画系统 v3 (AnimFSM)
│   │   │       ├── AnimFSM/                      # 动画有限状态机
│   │   │       ├── AnimStateController/          # 动画状态控制器
│   │   │       └── AnimStateControllerDP/        # 数据包动画状态控制器
│   │   ├── player_form/       (43 files)         # 玩家形态系统 (核心)
│   │   │   ├── ability/                          # 形态能力管理
│   │   │   ├── effect/                           # 形态变换效果
│   │   │   ├── forms/          (20 files)        # 具体形态实现
│   │   │   ├── instinct/                         # 本能系统
│   │   │   ├── skin/                             # 皮肤设置
│   │   │   └── transform/                        # 变形管理器
│   │   ├── player_form_render/ (15 files)        # 形态渲染 (OriginalFur)
│   │   ├── recipes/           (5 files)          # 自定义配方
│   │   ├── render/            (8 files)          # 渲染系统
│   │   │   ├── render_layer/                     # 渲染层 (毛皮渐变)
│   │   │   └── tech/                             # 技术性渲染
│   │   ├── screen_effect/     (2 files)          # 屏幕特效
│   │   ├── status_effects/    (18 files)         # 状态效果
│   │   │   ├── attachment/                       # 效果附着管理
│   │   │   ├── other_effects/                    # 其他效果
│   │   │   └── transformative_effects/           # 变形相关效果
│   │   ├── team/              (2 files)          # 队伍系统
│   │   └── util/              (19 files)         # 工具类
│   └── resources/
│       ├── fabric.mod.json                       # Fabric 模组元数据
│       ├── shape-shifter-curse.accesswidener     # 访问拓宽器
│       └── assets/                               # 资源文件
│           ├── shape-shifter-curse/              # 主资源包
│           │   ├── animations/                   # AzureLib 动画
│           │   ├── geo/                          # Blockbench 模型
│           │   ├── lang/                         # 语言文件
│           │   ├── models/                       # 实体/物品/方块模型
│           │   ├── owo_ui/                       # Owo UI 定义
│           │   ├── player_animation/             # 玩家动画资源
│           │   ├── rich_lang/                    # Rich Translatable Text
│           │   ├── shaders/                      # 自定义着色器
│           │   └── textures/                     # 贴图
│           ├── origins/                          # Origins UI 资源
│           └── orif-defaults/                    # OriginalFur 默认资源
│       └── data/                                 # 数据包内容
│           ├── shape-shifter-curse/
│           │   ├── advancements/                 # 进度
│           │   ├── origins/                      # 起源定义 (数据包)
│           │   ├── powers/                       # 能力定义 (数据包)
│           │   ├── recipes/                      # 配方
│           │   ├── loot_tables/                  # 战利品表
│           │   └── tags/                         # 标签
│           ├── origins/                          # Origins 内置数据
│           └── trinkets/                         # Trinkets 槽位定义
├── tools/                                        # 开发辅助工具 (Python)
│   ├── AlignLang.py                              # 语言文件对齐
│   ├── GenerateRideAnim.py                       # 骑乘动画生成
│   ├── LangMissingCheck.py                       # 语言文件缺失检查
│   ├── MaskProcess.py / MaskProcessV2/           # 贴图遮罩处理
│   ├── RichLang2Lang.py                          # Rich文本格式转换
│   └── rgb_colormask_maker.py                    # 颜色遮罩制作
├── 3d_models/                                    # Blockbench 3D模型源文件
├── dev/curio_integration/                        # Curio集成开发计划
├── libs/                                         # 本地 Jar 依赖
└── PatronServer/                                 # Patron服务器相关
```

---

## 核心系统架构

### 1. 入口系统 (Entry Points)

模组通过 `fabric.mod.json` 定义多个入口点：

| 入口点 | 类 | 说明 |
|--------|------|------|
| `main` | `ShapeShifterCurseFabric` | 主模组初始化 |
| `main` | `Origins` | 内置起源系统初始化 |
| `main` | `OriginalFur` | 毛皮渲染系统初始化 |
| `client` | `ShapeShifterCurseFabricClient` | 客户端初始化 |
| `client` | `OriginsClient` | 起源客户端初始化 |
| `client` | `OriginalFurClient` | 毛皮渲染客户端初始化 |
| `modmenu` | `ConfigIntegration` | ModMenu 配置集成 |
| `cardinal-components-entity` | 6个组件注册 | CCA 实体组件 |

Mixin 配置文件 (4个):
- `shape-shifter-curse.mixins.json` — 主 Mixin
- `origins.mixins.json` — 起源 Mixin
- `originalfur.fabric.mixins.json` — 毛皮渲染 Mixin
- `ssc-misc.mixins.json` — 杂项 Mixin

### 2. 玩家形态系统 (`player_form`) — 核心

形态系统是整个模组的核心，定义了玩家可以变身的各种形态。

**核心类：**
- `RegPlayerForms` — 注册所有内置形态（蝙蝠、美西螈、豹猫、狐狸、狼、蜘蛛等），每种形态有 4 个阶段（Phase 0-3）和特殊形态（SP）
- `PlayerFormBase` — 形态基类
- `PlayerFormGroup` — 形态分组
- `FormDataPackReloadListener` — 数据包动态加载形态

**形态阶段 (PlayerFormPhase):**
- `PHASE_0` ~ `PHASE_3` — 渐进的四个阶段，越高越接近完全变形
- `PHASE_SP` — 特殊形态（如 Allay、Feral Cat 等独立形态）

**已实现的形态路线（每路线4个阶段 + 特殊形态）：**
| 路线 | Phase 0-3 | 特殊形态 |
|------|-----------|----------|
| 蝙蝠 (Bat) | bat_0 ~ bat_3 | — |
| 美西螈 (Axolotl) | axolotl_0 ~ axolotl_3 | — |
| 豹猫 (Ocelot) | ocelot_0 ~ ocelot_3 | feral_cat_sp |
| 赤狐 (Familiar Fox) | familiar_fox_0 ~ familiar_fox_3 | — |
| 雪狐 (Snow Fox) | snow_fox_0 ~ snow_fox_3 | — |
| 阿努比斯狼 (Anubis Wolf) | anubis_wolf_0 ~ anubis_wolf_3 | — |
| 蜘蛛 (Spider) | spider_0 ~ spider_3 | — |
| Allay | — | allay_sp |
| 原始变形者 (Original) | original_before_enable, original_shifter | — |

**子模块功能：**
- `ability/` — 形态能力管理（FormAbilityManager, RegPlayerFormComponent）
- `effect/` — 变形效果处理（PlayerTransformEffectManager）
- `forms/` — 20个具体形态类实现
- `instinct/` — 本能系统（InstinctTicker, RegPlayerInstinctComponent）
- `skin/` — 皮肤设置（RegPlayerSkinComponent）
- `transform/` — 变形管理器（TransformManager）

### 3. Apoli 扩展能力系统 (`additional_power`) — 89 个文件

基于 Apoli 框架实现的自定义能力类型，涵盖：

| 类别 | 示例能力 |
|------|----------|
| **移动** | CrawlingPower, AlwaysSprintSwimmingPower, LevitatePower, TripleJumpPower |
| **战斗** | ChargePower, CriticalDamageModifierPower, VirtualShieldPower, VirtualTotemPower |
| **环境** | BreathingUnderWaterPower, HoldBreathPower, WaterFlexibilityPower |
| **交互** | ItemStorePower, CustomEdiblePower, AttractByEntityPower |
| **属性修改** | ManaAttributePower, ScalePower, ConditionScalePower |
| **特殊** | RenderTrinketsSlotPower, NoRenderArmPower, BatBlockAttachPower |
| **条件/动作** | AdditionalEntityConditions, AdditionalEntityActions, AdditionalItemCondition |

所有能力均注册为 Apoli PowerType，可在数据包中通过 JSON 配置使用。

### 4. 内置 Origins 起源系统 (`integration/origins`) — 71 个文件

模组内置了完整的 Origins 起源系统（forked from Apace100/origins），不是作为依赖而是直接集成。

**核心组件：**
- `Origin` / `OriginLayer` / `OriginLayers` — 起源、层级定义
- `OriginManager` — 起源数据包加载管理
- `OriginRegistry` — 起源注册中心
- `PlayerOriginComponent` — CCA 玩家起源组件
- `ChooseOriginScreen` / `ViewOriginScreen` — 起源选择/查看 UI

**徽章系统 (badge/):**
- 支持 Sprite 徽章、Keybind 徽章、Tooltip 徽章、CraftingRecipe 徽章
- BadgeManager 统一管理

**能力类型 (power/):**
- `OriginsPowerTypes` — 注册所有起源能力
- `OriginsEntityConditions` — 注册所有实体条件

**注册系统 (registry/):**
- ModBlocks, ModItems, ModEntities, ModEnchantments, ModTags, ModLoot, ModDamageSources

### 5. 给予形态的实体系统 (`form_giving_custom_entity`)

特殊的实体类型，用于给予玩家初始形态：

| 实体 | 注册名 | 生成组 |
|------|--------|--------|
| 变形蝙蝠 | `t_bat` | AMBIENT |
| 变形美西螈 | `t_axolotl` | AXOLOTLS |
| 变形豹猫 | `t_ocelot` | MONSTER |
| 变形狼 | `t_wolf` | CREATURE |
| 变形蜘蛛 | `t_spider` | MONSTER |

每个实体都有对应的：渲染器、刷怪蛋、生成配置。

### 6. 玩家动画系统 (`player_animation`)

使用 Player Animation Library 实现的动画系统，经历了三个版本的迭代：

- `v1 (form_animation/)` — 最初的形态动画
- `v2` — 改进版
- `v3/AnimFSM/` — 最新的动画有限状态机
  - `AnimStateController` — 动画状态控制器
  - `AnimStateControllerDP` — 数据包驱动的动画状态控制器

`RegPlayerAnimation` 负责注册和初始化动画。

### 7. 渲染系统

**形态渲染 (`player_form_render/`):**
- `OriginalFur` / `OriginalFurClient` — 基于 Origins 的毛皮渲染系统
- 支持渐变毛发着色、形态模型覆盖

**渲染层 (`render/render_layer/`):**
- `FurGradientRenderLayer` — 自定义毛发渐变着色器 (使用 Satin)

**技术渲染 (`render/tech/`):**
- 包含 AzureLib 模型渲染、着色器程序管理

### 8. 法力系统 (`mana`) — 10 个文件

完整的法力值系统：
- `ManaUtils` — 法力工具类，处理法力计算和 tick
- `ManaRegistries` / `ManaRegistriesClient` — 法力系统注册
- `RegManaComponent` — CCA 法力组件
- `ManaTypePower` / `ManaAttributePower` / `ConditionedManaAttributePower` — 法力相关能力

### 9. 召唤物系统 (`minion`) — 12 个文件

管理玩家召唤物（如阿努比斯狼召唤物）：
- `MinionRegister` / `MinionRegisterClient` — 召唤物注册
- `mobs/` — 5个召唤物实体类型

### 10. 状态效果系统 (`status_effects`) — 18 个文件

**子模块：**
- `attachment/` — 效果附着管理（EffectManager）
- `transformative_effects/` — 变形状态效果实现
- `other_effects/` — 其他状态效果
- `RegTStatusEffect` / `RegTStatusPotionEffect` / `RegOtherStatusEffects` — 注册类

### 11. 物品系统 (`items`) — 50 个文件

| 类别 | 说明 |
|------|------|
| `armors/` | 变形鳞甲 (Morphscale) 和下界合金变形鳞甲 (Netherite Morphscale) |
| `trinkets/` | 13 个 Trinkets 饰品文件，包含各类变形护符 |
| `tools/` | 10 个工具类（武器、法杖等） |
| `accessory/` | 3 个饰品文件 |

### 12. 自定义 UI (`custom_ui`) — 11 个文件

- `BookOfShapeShifterScreenV2_P1` — 变形者之书 V2（新版 UI）
- `StartBookScreenV2` — 启动书籍屏幕 V2
- 反射方式兼容旧版 Owo-lib UI
- 子模块 `util/` 包含 UI 工具类

### 13. 网络系统 (`networking`) — 4 个文件

- `ModPackets` — 数据包 ID 常量
- `ModPacketsC2S` — 客户端到服务端数据包
- `ModPacketsS2C` — 服务端到客户端数据包
- `ModPacketsS2CServer` — 服务端发送的广播数据包

### 14. Mixin 注入系统 (`mixin`) — 96 个文件

按功能分类的 Mixin：

| 子包 | 文件数 | 用途 |
|------|--------|------|
| `mob/` | 7 | 生物行为注入（如动物跟随、友好关系） |
| `integration/` | 4 | 第三方模组集成 |
| `accessor/` | 3 | 访问私有字段/方法 |
| `accessory/` | 2 | 饰品系统 |
| `block/` | 2 | 方块行为 |
| `compatibility/` | 2+ | 第一人称模型兼容等 |
| `projectile/` | 2 | 弹射物行为 |
| `plugin/` | 1 | Mixin 插件 |
| `test/` | 1 | 测试 |

### 15. 进度系统 (`advancement`) — 19 个文件

自定义进度触发器，标记玩家在变形过程中的各种里程碑：
- 首次安装模组、打开变形者之书
- 触发诅咒之月、结束诅咒之月、治愈诅咒之月
- 各阶段变形、通过催化剂变形、通过治疗变形
- 使用金苹果、变形效果消退

### 16. 配置系统 (`config`) — 5 个文件

使用 Cloth Config + Auto Config：
- `PlayerCustomConfig` — 玩家自定义配置（客户端）
- `ClientConfig` — 客户端配置
- `CommonConfig` — 双端通用配置
- `ConfigIntegration` — ModMenu 集成入口

---

## 关键依赖

| 依赖 | 版本 | 用途 |
|------|------|------|
| Fabric API | 0.92.3+1.20.1 | Fabric 基础 API |
| Apoli | 2.9.2+mc.1.20.x | 能力/条件/动作框架（核心依赖） |
| Pehkui | 3.7.8 | 实体缩放 |
| AzureLib | 3.0.19 | 3D 模型渲染（GeckoLib 分支） |
| Cardinal Components API | 5.1.0 | 实体数据组件 |
| Player Animation Lib | 1.0.2-rc1+1.20 | 玩家动画 |
| Cloth Config | 10.0.96 | 配置 UI |
| Satin | 1.14.0 | 自定义着色器 |
| Reach Entity Attributes | 2.4.0 | 触及距离属性 |
| ModMenu | 7.2.2 | 模组菜单集成 |
| owo-lib | 0.11.2+1.20 | UI 框架 (compileOnly) |
| Trinkets | 3.7.2 | 饰品槽位 (compileOnly) |

## 编译时兼容模组 (compileOnly)

- Iris (光影)
- Extra Origins (额外起源)
- Origins Classes (起源职业)
- Bendy Lib
- Better Combat
- Player Animator
- First-person Model
- AppleSkin
- ViveCraft
- TaCZ: Refabricated (枪械模组)

---

## 数据包系统

模组大量使用数据包（DataPack）来配置内容，支持通过数据包添加/修改：

- **形态定义** (`data/*/origins/`) — 起源和形态层级
- **能力定义** (`data/*/powers/`) — Apoli 能力 JSON
- **饰品能力** (`data/*/accessory_power/`) — 饰品附带的能力
- **配方** (`data/*/recipes/`) — 自定义合成表
- **标签** (`data/*/tags/`) — 实体、方块、物品、伤害类型标签
- **战利品表** (`data/*/loot_tables/`) — 方块/实体掉落
- **进度** (`data/*/advancements/`) — 自定义进度

动态形态加载：`FormDataPackReloadListener` 监听数据包重新加载事件，从数据包中读取新形态并注册。

---

## 开发辅助工具 (`tools/`)

| 工具 | 功能 |
|------|------|
| `AddFormSkill.md` | 添加新形态的详细指南文档 |
| `AlignLang.py` | 语言文件键值对齐工具 |
| `GenerateRideAnim.py` | 骑乘动画 JSON 生成 |
| `LangMissingCheck.py` | 语言文件缺失翻译检查 |
| `MaskProcess.py` | 贴图遮罩处理 |
| `MaskProcessV2/` | 贴图遮罩处理 V2 |
| `MaskProcessV2_C/` | 贴图遮罩处理 V2 C 版 |
| `RichLang2Lang.py` | Rich Translatable Text 格式转换 |
| `rgb_colormask_maker.py` | RGB 颜色遮罩生成 |
| `form_riding_animation.json` | 骑乘动画配置模板 |

---

## 关键技术点

1. **CCA (Cardinal Components API)** — 6 个自定义实体组件用于持久化玩家数据（形态、本能、皮肤、召唤物、法力、起源选择）

2. **Apoli 集成** — 模组深度依赖 Apoli 的能力系统，所有形态技能通过 Apoli PowerType 实现，支持条件(Condition)和动作(Action)的组合

3. **数据包驱动** — 形态和能力的核心数据可通过数据包定义，实现高度的可扩展性和社区自定义

4. **Satin 着色器** — 使用自定义 GLSL 着色器实现毛发渐变效果

5. **Player Animation Lib** — 通过 AnimFSM (动画有限状态机) 管理形态变换和特殊动作的动画

6. **AzureLib (GeckoLib 分支)** — 用于实体和护甲的 3D 模型渲染，支持 `.geo.json` 和 `.animation.json`
