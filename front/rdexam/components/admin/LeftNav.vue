<template>
    <el-menu :default-openeds="['1']">
      <el-submenu index="1">
        <span slot="title"><i class="el-icon-message"></i>题库管理</span>
        <el-menu-item-group>
          <span slot="title">研发题库</span>
          <el-menu-item index="1-1">前端技术</el-menu-item>
          <el-menu-item index="1-2">后端技术</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="测试题库">
          <el-menu-item index="1-3">测试知识</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="综合题库">
          <el-menu-item index="1-4">传输业务</el-menu-item>
          <el-menu-item index="1-5">运维环境</el-menu-item>
          <el-menu-item index="1-6">软件工程</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <span slot="title"><i class="el-icon-menu"></i>查询统计</span>
        <el-menu-item-group>
          <span slot="title">完成情况查询</span>
          <el-menu-item index="2-1">当月完成情况</el-menu-item>
          <el-menu-item index="2-2">历月完成情况</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group>
          <span slot="title">综合统计</span>
          <el-menu-item index="2-3">按人员统计</el-menu-item>
          <el-menu-item index="2-4">按题库统计</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <span slot="title"><i class="el-icon-setting"></i>系统管理</span>
        <el-menu-item-group>
          <el-menu-item index="3-1">角色管理</el-menu-item>
          <el-menu-item index="3-2">组织机构管理</el-menu-item>
          <el-menu-item index="3-3">人员管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
</template>
<script>
    export default {
        layout: 'admin/nav'
    }
</script>
