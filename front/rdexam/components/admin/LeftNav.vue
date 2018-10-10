<template>
     <el-aside width="200px">
        <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
          <el-radio-button :label="false">展开</el-radio-button>
          <el-radio-button :label="true">收起</el-radio-button>
        </el-radio-group>
        <el-menu :default-openeds="['1']" class="left-menu" :collapse="isCollapse" @select="handleSelect">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i><span slot="title">题库管理</span></template>
            <el-menu-item-group>
              <span slot="title">研发题库</span>
              <el-menu-item index="webtec">前端技术</el-menu-item>
              <el-menu-item index="javatec">后端技术</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="测试题库">
              <el-menu-item index="testtec">测试知识</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="综合题库">
              <el-menu-item index="transtec">传输业务</el-menu-item>
              <el-menu-item index="opertec">运维环境</el-menu-item>
              <el-menu-item index="setec">软件工程</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i><span slot="title">查询统计</span></template>
            <el-menu-item-group>
              <span slot="title">完成情况查询</span>
              <el-menu-item index="thismonth">当月完成情况</el-menu-item>
              <el-menu-item index="allmonth">历月完成情况</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group>
              <span slot="title">综合统计</span>
              <el-menu-item index="byperson">按人员统计</el-menu-item>
              <el-menu-item index="byquestion">按题库统计</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i><span slot="title">系统管理</span></template>
            <el-menu-item-group>
              <el-menu-item index="rolemanage">角色管理</el-menu-item>
              <el-menu-item index="orgmanage">组织机构管理</el-menu-item>
              <el-menu-item index="usermanage">人员管理</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
    </el-aside>
</template>
<script>
    export default {
        data() {
           return {
             isCollapse: this.$store.state.admin.menuCollpse
           };
        },
        methods: {
          handleSelect(key){
            this.$store.commit('admin/addTab',key)
          }
        }
    }
</script>
<style>
.left-menu:not(.el-menu--collapse){
    width: 200px;
    min-height: 500px;
}
</style>
