<template>
  <el-main>
    <el-tabs v-model="activeTabName" type="card" closable @tab-remove="removeTab">
      <el-tab-pane
        v-for="(item, index) in tabDatas"
        :key="item.name"
        :label="item.title"
        :name="item.name"
      >
        <keep-alive>
          <component :is=item.content></component>
        </keep-alive>
      </el-tab-pane>
    </el-tabs>
  </el-main>
</template>
<script>
  export default {
    computed: {
      activeTabName: {
        get: function () {
          return this.$store.state.admin.activeTabName
        },
        set: function (newTabName) {
          this.$store.commit('admin/activeTab', newTabName)
        }
      },
      tabDatas() {
        return this.$store.state.admin.tabDatas
      }
    },
    methods: {
      removeTab(targetName) {
        this.$store.commit('admin/removeTab', targetName)
        targetName = null
      }
    }
  }
</script>
