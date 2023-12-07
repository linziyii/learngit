<template>
  <a-layout>
      <the-header></the-header>
      <router-view/>
    <a-layout-footer style="text-align: center">
      <span>MyKi ©2023</span>
      <div class="a-item" style="display: flex; justify-content: center; gap: 10px; color: #888888;">
        <a href="">关于我们</a>
        <a href="">加入我们</a>
        <a href="">意见建议</a>
      </div>   
    </a-layout-footer>
  </a-layout>
</template>

<script lang="ts" >
import { ref } from 'vue';
import { defineComponent } from 'vue';
import TheHeader from '@/components/the-header.vue';
import theHeader from './components/the-header.vue';
import { UserOutlined, LaptopOutlined, NotificationOutlined } from '@ant-design/icons-vue';
const selectedKeys1 = ref<string[]>(['2']);
const selectedKeys2 = ref<string[]>(['1']);
const openKeys = ref<string[]>(['sub1']);
  export default defineComponent({
    name: 'app',
    components: {
      TheHeader,
    },
  });
</script>

<style scoped>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
.a-item a{
  color: gray;
}
</style>
