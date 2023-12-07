<template>
    <a-layout-footer style="text-align: center">
      <span>MyKi ©2023</span>
      <div class="a-item" style="display: flex; justify-content: center; gap: 10px; color: #888888;">
        <a href="">关于我们</a>
        <a href="">加入我们</a>
        <a href="">意见建议</a>
      </div>   
    </a-layout-footer>
</template>

<script lang="ts">
import { ref } from 'vue';
import { defineComponent } from 'vue'; 
  export default defineComponent({
  name: 'the-footer',
});
</script>