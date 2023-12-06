# MyKi (My wiki)项目

#### 介绍

精简的个人知识库系统

#### 拉去自己分支项目

~~~
git clone -b 自己分支名 https://github.com/linziyii/learngit.git
~~~

#### 提交流程

~~~
git pull // 更新代码
git add .
git commit -m "" // 提交代码 一般feat: 
git push // 一定切换自己分支
~~~

#### 提交规范

~~~
'feat', // 新功能 feature
'fix', // 修复 bug
'docs', // 文档注释
'style', // 代码格式(不影响代码运行的变动)
'refactor', // 重构(既不增加新功能，也不是修复bug)
'perf', // 性能优化
'test', // 增加测试
'chore', // 构建过程或辅助工具的变动
'revert', // 回退
'build' // 打包
~~~

#### 合并到主分支代码并提交

- 先从自己分支切换到master分支

~~~
git checkout master
~~~

- 合并到主分支

~~~
git merge 自己分支名
~~~

- 提交代码

~~~
git push
~~~

注意： 开发切换到自己分支，合并需要切到master分支

## 项目结构

#### 前端

#### 后端

## 项目展示
