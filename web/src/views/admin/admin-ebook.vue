<template>
  <a-layout>
    <a-layout-content
      :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-table
        :columns="columns"    
        :data-source="ebooks"
        :pagination="pagination"
        :loading="loading"
        @change="handleTableChange"
      >
      <template #headerCell="{ column }">
        <template v-if="column.key === 'cover'">
          <span>
            封面
          </span>
        </template>     
      </template>

      <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'cover'">
            <span><img  v-if="record.cover" :src="record.cover" /></span>  
          </template>
          <template v-else-if="column.key === 'tags'">
            <span>
              <a-tag
                v-for="tag in record.tags"
                :key="tag"
                :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
              >
                {{ tag.toUpperCase() }}
              </a-tag>
            </span>
          </template>
          <template v-else-if="column.key === 'action'">
            <span>
              <a-space size="small">
                <a-button type="primary" @click="edit(record)">
                  编辑
                </a-button>
                <a-button type="primary" danger block>
                  删除
                </a-button>
              </a-space>
            </span>    
          </template>
      </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  
  <a-modal
    title="电子书表单"
    v-model:visible="modalVisible"
    :confirm-loading="modalLoading"
    @ok="handleModalOk"
  >
  <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="分类一">
        <a-input v-model:value="ebook.category1Id" />
      </a-form-item>
      <a-form-item label="分类二">
        <a-input v-model:value="ebook.category2Id" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.desc" type="textarea" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
  import { defineComponent, onMounted, ref } from 'vue';
  import axios from 'axios';

  export default defineComponent({
    name: 'AdminEbook',
    setup() {
      const ebooks = ref();
      const pagination = ref({
        current: 1,
        pageSize: 3,
        total: 0
      });
      const loading = ref(false);

      const columns = [
        {
          title: '封面',
          dataIndex: 'cover',
          key: 'cover',
        },
        {
          title: '名称',
          dataIndex: 'name',
          key: 'name', // 使用 dataIndex 作为 key
        },
        {
          title: '分类一',
          key: 'category1Id',
          dataIndex: 'category1Id',
        },
        {
          title: '分类二',
          dataIndex: 'category2Id',
          key: 'category2Id', // 使用 dataIndex 作为 key
        },
        {
          title: '文档数',
          dataIndex: 'docCount',
          key: 'docCount', // 使用 dataIndex 作为 key
        },
        {
          title: '阅读数',
          dataIndex: 'viewCount',
          key: 'viewCount', // 使用 dataIndex 作为 key
        },
        {
          title: '点赞数',
          dataIndex: 'voteCount',
          key: 'voteCount', // 使用 dataIndex 作为 key
        },
        {
          title: '操作',
          key: 'action',
          dataIndex: 'action',
        },
      ];


      /**
       * 数据查询
       **/
      const handleQuery = (params: any) => {
        loading.value = true;
        axios.get("/ebook/list", {
          params: {
            page: params.page,
            size: params.size
          }
        }).then((response) => {
          loading.value = false;
          const data = response.data;
          ebooks.value = data.content.list;

          // 重置分页按钮
          pagination.value.current = params.page;
          pagination.value.total = data.content.total;
        });
      };

      /**
       * 表格点击页码时触发
       */
      const handleTableChange = (pagination: any) => {
        console.log("看看自带的分页参数都有啥：" + pagination);
        handleQuery({
          page: pagination.current,
          size: pagination.pageSize
        });
      };
 
      // -------- 表单 ---------
      const ebook = ref({}) as any;      
      const modalVisible = ref(false);
      const modalLoading = ref(false);
      const handleModalOk = () => {
        modalLoading.value = true;
        axios.post("/ebook/save", ebook.value).then((response) => {
          const data = response.data; // data = commonResp
          if (data.success) {
            modalVisible.value = false;
            modalLoading.value = false;

            // 重新加载列表
            handleQuery({
              page: pagination.value.current,
              size: pagination.value.pageSize,
            });
          }
        });
      };

      /**
       * 编辑
       */
      const edit = (record: any) => {
        modalVisible.value = true;
        ebook.value = record
      };

      onMounted(() => {
        handleQuery({
          page: 1,
          size: pagination.value.pageSize,
        });
      });

      return {
        ebooks,
        pagination,
        columns,
        loading,
        handleTableChange,

        edit,

        ebook,
        modalVisible,
        modalLoading,
        handleModalOk
      }
    }
  });
</script>

<style scoped>
  img {
    width: 50px;
    height: 50px;
  }
</style>
