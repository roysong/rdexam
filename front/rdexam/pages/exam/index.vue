<template>
  <div>
    <user-info></user-info>
    <exam-region></exam-region>
  </div>
</template>

<script>
    import UserInfo from "~/components/exam/UserInfo"
    import ExamRegion from "~/components/exam/ExamRegion"
    export default {
        name: "exam",
      components:{UserInfo,ExamRegion}
    }
</script>

<style scoped>

</style>
