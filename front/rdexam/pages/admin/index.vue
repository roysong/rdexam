<template>
    <el-container class="adminMain">
        <left-nav />
    </el-container>
</template>

<script>
import LeftNav from '~/components/admin/LeftNav'
export default {
  components: {LeftNav}
}
</script>

<style>
.adminMain{
    height: 500px;
    border: 1px solid #eee;
}
</style>
