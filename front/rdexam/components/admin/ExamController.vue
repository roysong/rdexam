<template>
  <div>
    <exam-search/>
    <exam-list/>
  </div>
</template>

<script>
  import ExamList from '~/components/admin/ExamList'
  import ExamSearch from '~/components/admin/ExamSearch'

  export default {
    name: "ExamController",
    components: {ExamList, ExamSearch}
  }
</script>

<style scoped>

</style>
