<template>
    <a-layout>
      <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        <p>
          <a-form layout="inline" :model="param">
            <a-form-item>
              <!-- <a-input v-model:value="param.name" placeholder="名称">
              </a-input> -->
              <a-input-search
                v-model:value="param.name"
                placeholder=" 查询书名"
                enter-button
                @search="handleQuery()"
              />
            </a-form-item>
            <!-- <a-form-item>
              <a-button type="primary" @click="handleQuery({page: 1, size: pagination.pageSize})">
                查询
              </a-button>
            </a-form-item> -->
            <a-form-item>
              <a-button type="primary" @click="add()">
                新增
              </a-button>
            </a-form-item>
          </a-form>
        </p>
        <a-table
          :columns="columns" 
          :row-key="record => record.id"   
          :data-source="level1"
          :loading="loading"
          :pagination="false"
        >
        <template #headerCell="{ column }">
          <template v-if="column.key === 'name'">
            <span>
              名称
            </span>
          </template>     
        </template>

        <template #bodyCell="{ column, record }">
          <!-- <template v-if="column.key === 'parent' || column.key === 'name' || column.key === 'sort'">
            <span>{{ record.parent }}</span>  
          </template> -->
            <template v-if="column.key === 'action'">
              <span>
                <a-space size="small">
                  <a-button type="primary" @click="edit(record)">
                    编辑
                  </a-button>
                  <!-- <a-button type="primary" danger block>
                    删除
                  </a-button> -->
                  <a-popconfirm
                    title="删除后不可恢复，确认删除?"
                    ok-text="是"
                    cancel-text="否"
                    @confirm="handleDelete(record.id)"
                  >
                    <a-button type="primary" danger block>
                      删除
                    </a-button>
                  </a-popconfirm>
                </a-space>
              </span>    
            </template>
          </template>
        </a-table>
      </a-layout-content>
    </a-layout>

  
    <a-modal
      title="分类表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
    >
    <a-form :model="category" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
        <a-form-item label="名称">
          <a-input v-model:value="category.name" />
        </a-form-item>
        <a-form-item label="父分类">
          <a-input v-model:value="category.parent" />
        </a-form-item>
        <a-form-item label="顺序">
          <a-input v-model:value="category.sort" />
        </a-form-item>
      </a-form>
    </a-modal>
  </template>

  <script lang="ts">
    import { defineComponent, onMounted, ref } from 'vue';
    import axios from 'axios';
    import { message } from 'ant-design-vue';
    import {Tool} from "@/util/tool";

    export default defineComponent({
      name: 'AdminCategory',
      setup() {
        const param = ref();
        param.value = {};
        const categorys = ref();
        const loading = ref(false);

        const columns = [
          {
            title: '名称',
            key: 'name',
            dataIndex: 'name'
          },
          {
            title: '父分类',
            key: 'parent',
            dataIndex: 'parent'
          },
          {
            title: '顺序',
            key: 'sort',
            dataIndex: 'sort'
          },
          {
            title: 'Action',
            key: 'action',
            dataIndex: 'action'
              //slots: { customRender: 'action' }
          }
        ];

        /**
         * 一级分类树，children属性就是二级分类
         * [{
         *   id: "",
         *   name: "",
         *   children: [{
         *     id: "",
         *     name: "",
         *   }]
         * }]
         */
          const level1 = ref();   //一级分类树，children属性就是二级分类

        /**
         * 数据查询
         **/
        const handleQuery = () => {
          loading.value = true;
          axios.get("/category/all").then((response) => {
            loading.value = false;
            const data = response.data;
            if (data.success) {
              categorys.value = data.content;
              console.log("原始数组：", categorys.value);

              level1.value = [];
              level1.value = Tool.array2Tree(categorys.value, 0);
              console.log("树形结构：", level1);
            } else {
              message.error(data.message);
            }
          });
        };
  
          //-------- 表单 ---------
        const category = ref({}) as any;      
        const modalVisible = ref(false);
        const modalLoading = ref(false);
        const handleModalOk = () => {
          modalLoading.value = true;
          axios.post("/category/save", category.value).then((response) => {
            modalLoading.value = false;
            const data = response.data;   //data = commonResp
            if (data.success) {
              modalVisible.value = false;
              modalLoading.value = false;

                //重新加载列表
              handleQuery();
            } else {
              message.error(data.message);
            }
          });
        };

        /**
         * 编辑
         */
        const edit = (record: any) => {
          modalVisible.value = true;
          category.value = Tool.copy(record);
        };

        /**
         * 新增
         */
          const add = () => {
          modalVisible.value = true;
          category.value = {};
        };
      
        const handleDelete = (id: number) => {
          console.log("id", id)
          axios.delete("/category/delete/" + id).then((response) => {
            const data = response.data;   //data = commonResp
            if (data.success) {
                //重新加载列表
              handleQuery();
            }
          });
        };

        onMounted(() => {
          handleQuery();
        });

        return {
          param,
          level1,
          columns,
          loading,
          handleQuery,

          edit,
          add,

          category,
          modalVisible,
          modalLoading,
          handleModalOk,
          handleDelete
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

