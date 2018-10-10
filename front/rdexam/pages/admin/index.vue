<template>
    <el-container class="adminMain">
        <left-nav />
        <right-tab />
    </el-container>
</template>

<script>
import LeftNav from '~/components/admin/LeftNav'
import RightTab from '~/components/admin/RightTab'
export default {
  components: {LeftNav,RightTab}
}
</script>

<style>
.adminMain{
  border: 1px solid #eee;
  width: 100%;
}
</style>
