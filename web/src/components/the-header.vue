<template>
  <a-layout-header class="header">
    <div class="logo">MyKi知识库

    </div>
    <div  class="log">
      <a-popconfirm
        title="确认退出登录?"
        ok-text="是"
        cancel-text="否"
        @confirm="logout()"
      >
        <a class="login-menu" v-show="user.id">
          <a-button type="primary">退出登录</a-button>
        </a>
      </a-popconfirm>
      <a class="login-menu-a" v-show="user.id">
        <span>您好：{{user.name}}</span>

        <a-avatar :size="{ xs: 12, sm: 24, md: 32, lg: 48, xl: 48, xxl: 48 }" style="background-color: rgba(22, 119,255); margin-left: 10px;">
          <template #icon>
            <AntDesignOutlined />
          </template>
        </a-avatar>
      </a>
      <a class="login-menu-login" v-show="!user.id" @click="showLoginModal">
        <a-button type="primary">登录</a-button>
      </a>
    </div>
    <a-menu
      theme="dark"
      mode="horizontal"
      :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/">
        <router-link to="/">首页</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/user" :style="user.id? {} : {display:'none'}">
        <router-link to="/admin/user">用户管理</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/ebook" :style="user.id? {} : {display:'none'}">
        <router-link to="/admin/ebook">电子书管理</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/category" :style="user.id? {} : {display:'none'}">
        <router-link to="/admin/category">分类管理</router-link>
      </a-menu-item>
      <a-menu-item key="/about">
        <router-link to="/about">关于我</router-link>
      </a-menu-item>



    </a-menu>

    <a-modal
      title="登录"
      v-model:visible="loginModalVisible"
      :confirm-loading="loginModalLoading"
      @ok="login"
    >
      <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
        <a-form-item label="登录名">
          <a-input v-model:value="loginUser.loginName" />
        </a-form-item>
        <a-form-item label="密码">
          <a-input v-model:value="loginUser.password" type="password" />
        </a-form-item>
      </a-form>
    </a-modal>
  </a-layout-header>
</template>

<script lang="ts">
  import { defineComponent, ref, computed } from 'vue';
  import axios from 'axios';
  import { message } from 'ant-design-vue';
  import store from "@/store";
  import { AntDesignOutlined } from '@ant-design/icons-vue';
  declare let hexMd5: any;
  declare let KEY: any;

  export default defineComponent({
    name: 'the-header',
    setup () {
      // 登录后保存
      const user = computed(() => store.state.user);

      // 用来登录
      const loginUser = ref({
        loginName: "test",
        password: "test"
      });
      const loginModalVisible = ref(false);
      const loginModalLoading = ref(false);
      const showLoginModal = () => {
        loginModalVisible.value = true;
      };

      // 登录
      const login = () => {
        console.log("开始登录");
        loginModalLoading.value = true;
        loginUser.value.password = hexMd5(loginUser.value.password + KEY);
        axios.post('/user/login', loginUser.value).then((response) => {
          loginModalLoading.value = false;
          const data = response.data;
          if (data.success) {
            loginModalVisible.value = false;
            message.success("登录成功！");

            store.commit("setUser", data.content);
          } else {
            message.error(data.message);
          }
        });
      };

      // 退出登录
      const logout = () => {
        console.log("退出登录开始");
        axios.get('/user/logout/' + user.value.token).then((response) => {
          const data = response.data;
          if (data.success) {
            message.success("退出登录成功！");
            store.commit("setUser", {});
          } else {
            message.error(data.message);
          }
        });
      };

      return {
        loginModalVisible,
        loginModalLoading,
        showLoginModal,
        loginUser,
        login,
        user,
        logout
      }
    }
  });
</script>

<style>
  .logo {
    width: 120px;
    height: 31px;
    /*background: rgba(255, 255, 255, 0.2);*/
    /*margin: 16px 28px 16px 0;*/
    float: left;
    color: white;
    font-size: 18px;
  }
  /* .login-menu {
    float: right;
    color: pink;
    padding-left: 10px;
  } */
    .login-menu {
    width: 120px;
    height: 31px;
    float: right;
    color: white;
    font-size: 16px;
  }
  .login-menu-a {
    width: 180px;
    height: 31px;
    float: right;
    color: white;
    font-size: 16px;
  }
  .login-menu-login {
      width: 120px;
    height: 31px;
    float: right;
    font-size: 18px;
  }
</style>
