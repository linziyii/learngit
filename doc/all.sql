create table `ebook` (
                         `id` bigint not null comment 'id',
                         `name` varchar(50) comment '名称',
                         `category1_id` bigint comment '分类1',
                         `category2_id` bigint comment '分类2',
                         `description` varchar(200) comment '描述',
                         `cover` varchar(200) comment '封面',
                         `doc_count` int not null default 0 comment '文档数',
                         `view_count` int not null default 0 comment '阅读数',
                         `vote_count` int not null default 0 comment '点赞数',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='电子书';

insert into `ebook` (id, name, description)
values (1, '西游记', '四人转');
insert into `ebook` (id, name, description) values (5, 'Spring Boot 入门教程', '零基础入门 Java 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (2, 'Vue 入门教程', '零基础入门 Vue 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (3, 'Python 入门教程', '零基础入门 Python 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (4, 'Mysql 入门教程', '零基础入门 Mysql 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (6, 'Oracle 入门教程', '零基础入门 Oracle 开发，企业级应用开发最佳首选框架');

create table `category` (
                            `id` bigint not null comment 'id',
                            `parent` bigint not null default 0 comment '父id',
                            `name` varchar(50) not null comment '名称',
                            `sort` int comment '顺序',
                            primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='分类';

insert into `category` (id, parent, name, sort) values (100, 000, '前端开发', 100);
insert into `category` (id, parent, name, sort) values (101, 100, 'Vue', 101);
insert into `category` (id, parent, name, sort) values (102, 100, 'HTML & CSS', 102);
insert into `category` (id, parent, name, sort) values (200, 000, 'Java', 200);
insert into `category` (id, parent, name, sort) values (201, 200, '基础应用', 201);
insert into `category` (id, parent, name, sort) values (202, 200, '框架应用', 202);
insert into `category` (id, parent, name, sort) values (300, 000, 'Python', 300);
insert into `category` (id, parent, name, sort) values (301, 300, '基础应用', 301);
insert into `category` (id, parent, name, sort) values (302, 300, '进阶方向应用', 302);
insert into `category` (id, parent, name, sort) values (400, 000, '数据库', 400);
insert into `category` (id, parent, name, sort) values (401, 400, 'MySQL', 401);
insert into `category` (id, parent, name, sort) values (500, 000, '其它', 500);
insert into `category` (id, parent, name, sort) values (501, 500, '服务器', 501);
insert into `category` (id, parent, name, sort) values (502, 500, '开发工具', 502);
insert into `category` (id, parent, name, sort) values (503, 500, '热门服务端语言', 503);

-- 文档表
drop table if exists `doc`;
create table `doc` (
                       `id` bigint not null comment 'id',
                       `ebook_id` bigint not null default 0 comment '电子书id',
                       `parent` bigint not null default 0 comment '父id',
                       `name` varchar(50) not null comment '名称',
                       `sort` int comment '顺序',
                       `view_count` int default 0 comment '阅读数',
                       `vote_count` int default 0 comment '点赞数',
                       primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='文档';

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (1, 1, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (2, 1, 1, '文档1.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (3, 1, 0, '文档2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (4, 1, 3, '文档2.1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (5, 1, 3, '文档2.2', 2, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (6, 1, 5, '文档2.2.1', 1, 0, 0);

-- 文档内容
drop table if exists `content`;
create table `content` (
                           `id` bigint not null comment '文档id',
                           `content` mediumtext not null comment '内容',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='文档内容';

create table `user` (
                        `id` bigint not null comment 'ID',
                        `login_name` varchar(50) not null comment '登陆名',
                        `name` varchar(50) comment '昵称',
                        `password` char(32) not null comment '密码',
                        primary key (`id`),
                        unique key `login_name_unique` (`login_name`)
) engine=innodb default charset=utf8mb4 comment='用户';

insert into `user` (id, `login_name`, `name`, `password`) values (1, 'test', '测试', 'e70e2222a9d67c4f2eae107533359aa4');

create table `ebook_snapshot` (
                                  `id` bigint auto_increment not null comment 'id',
                                  `ebook_id` bigint not null default 0 comment '电子书id',
                                  `date` date not null comment '快照日期',
                                  `view_count` int not null default 0 comment '阅读数',
                                  `vote_count` int not null default 0 comment '点赞数',
                                  `view_increase` int not null default 0 comment '阅读增长',
                                  `vote_increase` int not null default 0 comment '点赞增长',
                                  primary key (`id`),
                                  unique key `ebook_id_date_unique` (`ebook_id`, `date`)
) engine=innodb default charset=utf8mb4 comment='电子书快照表';
insert into `doc` (id,ebook_id, parent, name, sort, view_count, vote_count) values (26,7, 0, '
第一卷 石猴出世，悟道长生', 1, 0, 0);
insert into `doc` (id,ebook_id, parent, name, sort, view_count, vote_count) values (24,7, 26, '
第一回 灵根育孕源流出 心性修持大道生', 1, 0, 0);
insert into `content` (id,content) values (24,'东胜神洲傲来国海中有花果山，山项上一仙石孕育出一石猴。石猴在所居涧水源头寻到名为“水帘洞”的石洞，被群猴拥戴为王。又过三五百年，石猴忽为人生无常，不得久寿而悲啼。根据一老猴指点，石猴经南赡训洲到西牛贺洲，上灵台方寸山，入斜月三星洞，拜见须菩提祖师，被收为徒，起名曰孙悟空。');
insert into `doc` (id,ebook_id, parent, name, sort, view_count, vote_count) values (25,7, 26, '
第二回 悟彻菩提真妙理 断魔归本合元神', 2, 0, 0);
insert into `content` (id,content) values (25,'悟空从祖师学得长生之道、七十二般变化及“筋斗云”。一日，悟空受众人挑唆，变为松树，引起祖师不快，被逐出洞。回到花果山，与占山妖魔厮斗取胜，带回被掳的众猴与物品。');
insert into `doc` (id,ebook_id, parent, name, sort, view_count, vote_count) values (27,7, 0, '
第二卷 心比天高，扰天宫不得安宁', 2, 0, 0);
insert into `doc` (id,ebook_id, parent, name, sort, view_count, vote_count) values (28,7, 27, '
第三回 官封弼马心何足 名注齐天意未宁', 1, 0, 0);
insert into `content` (id,content) values (28,'悟空被授以“弼马温”之官，他得知此官为末等职，回花果山。玉帝命托塔李天王与其子哪吒太子擒悟空。被悟空打败。金星再次招来悟空。玉帝命造齐天大圣府，让悟空居住。');
insert into `doc` (id,ebook_id, parent, name, sort, view_count, vote_count) values (29,7, 27, '
第四回 乱蟠桃大圣偷丹 反天宫诸神捉怪', 2, 0, 0);
insert into `content` (id,content) values (29,'悟空管理蟠桃园，吃尽园中大桃。又赴瑶池，喝光仙酒；吃尽太上老君葫芦内的金丹。逃回花果山。玉帝令托塔天王率天兵去捉拿悟空。悟空打退了众天神。');
insert into `doc` (id,ebook_id, parent, name, sort, view_count, vote_count) values (30,7, 27, '
第五回 观音赴会问原因 小圣施威降大圣', 2, 0, 0);
insert into `content` (id,content) values (30,'南海观音徒弟木叉助天王，被战败。观音又荐二郎神。二郎神与悟空大战，太上老君在天观战，丢下金刚套，击中悟空。众神押其回上界。玉帝传旨处死。');
insert into `doc` (id,ebook_id, parent, name, sort, view_count, vote_count) values (31,7, 27, '
第六回 八卦炉中逃大圣 五行山下定心猿', 2, 0, 0);
insert into `content` (id,content) values (31,'太上老君将悟空置入炼丹炉烧炼，四十九天后，悟空出来，大闹天宫。玉帝请来如来佛。孙悟空一路筋斗云，跳不出佛掌。如来将五指化为大山，压住悟空。命土地神用铁丸铜汁饲喂悟空。');

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (40,2,0,'1、Vue简介',1,0,0),
       (41,2,40,'1-1 Vue框架特性',1,0,0),
       (42,2,40,'1-2 数据驱动视图',2,0,0),
       (43,2,40,'1-3 双向数据绑定',3,0,0),
       (44,2,40,'1-5 MVVM 的工作原理',4,0,0);
insert into `content`(id, content)
values (41,'数据驱动视图和双向数据绑定'),
       (42,'在使用了 vue 的页面中，vue 会监听数据的变化，从而自动重新渲染页面的结构'),
       (43,'在填写表单时，双向数据绑定可以辅助开发者在不操作 DOM 的前提下，自动把用户填写的内容同步到数据源中。'),
       (44,'ViewModel 作为 MVVM 的核心，是它把当前页面的数据源（Model）和页面的结构（View）连接在了一起');
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count)
values (45,2,0,'2、Vue的指令与过滤器',2,0,0),
       (46,2,45,'2-1 指令的概念',1,0,0),
       (47,2,45,'2-2 内容渲染指令',2,0,0),
       (48,2,45,'2-3 属性绑定指令',3,0,0);
insert into  `content`(id, content)
values (46,'指令（Directives）是 vue 为开发者提供的模板语法，用于辅助开发者渲染页面的基本结构。'),
       (47,'内容渲染指令用来辅助开发者渲染 DOM 元素的文本内容。'),
       (48,'如果需要为元素的属性动态绑定属性值，则需要用到 v-bind 属性绑定指令。');