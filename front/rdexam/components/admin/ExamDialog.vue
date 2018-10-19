<!--题库新增修改弹出框-->
<template>
  <el-dialog :title="title" :visible="visible" @close="closeDialog">
    <el-form :model="addForm">
      <el-form-item label="题目">
        <el-input v-model="addForm.title" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="选项1">
        <el-input v-model="addForm.options[0]" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="选项2">
        <el-input v-model="addForm.options[1]" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="选项3">
        <el-input v-model="addForm.options[2]" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="选项4">
        <el-input v-model="addForm.options[3]" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="答案">
        <el-input v-model="addForm.right" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="tags">
        <el-select
          v-model="addForm.tags"
          multiple
          filterable
          allow-create
          default-first-option
          placeholder="请选择题目标签">
          <el-option
            v-for="item in tagOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button type="primary" @click="addItem">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  export default {
    name: "ExamDialog",
    props: ['title', 'addForm','dialogFormVisible','tagOptions'],
    computed:{
      visible(){
        return this.dialogFormVisible
      }
    },
    methods:{
      addItem(){
        this.$emit('add-item')
      },
      closeDialog(){
        if(this.visible){
          this.$emit('close-dialog')
        }
      }
    }
  }
</script>

<style scoped>

</style>
