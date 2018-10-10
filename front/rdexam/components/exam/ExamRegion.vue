<!--答题界面题目信息及交互-->
<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>题目：{{this.$store.state.exam.nowExam.title}}</span>
      <div class="fl-r">
        <el-button type="success" icon="el-icon-check" size="small">交卷</el-button>
        <el-button @click="oneMore" type="info" size="small">再来一套</el-button>
      </div>
    </div>
    <div v-for="o in this.$store.state.exam.nowExam.options" :key="o" class="text item">
      <el-radio @change="isRightHandle" :disabled="radioDisabled" v-model="answer" :label="o" border>{{o}}</el-radio>
    </div>
    <el-button-group class="fl-r">
      <el-button @click="nextOne" type="primary">下一题<i class="el-icon-arrow-right el-icon--right"></i></el-button>
    </el-button-group>
  </el-card>
</template>

<script>
  export default {
    name: "ExamRegion",
    data() {
      return {
        answer: null,
        radioDisabled: false
      }
    },
    async created() {
      console.log(this.$store.state.user, 'user')
      let params = new URLSearchParams();
      params.append("userName", this.$store.state.user.userName);
      let {data} = await this.$axios.post("/getExam", params);
      if (data) {
        this.$store.commit("exam/updateExam", data);
      }
    },
    methods: {
      /**
       * 获取新的一套题目
       */
      oneMore() {
        console.log('再来一套')
      },
      /**
       * 下一道题，回答后才能点击下一道题
       */
      nextOne() {
        if (this.radioDisabled) {
          this.radioDisabled = false;
          this.answer = null;
        } else {
          this.$message.warning('请先作答');
        }
      },
      /**
       * 答题后判断是否回答正确
       */
      async isRightHandle() {
        this.radioDisabled = true;
        let params = new URLSearchParams();
        params.append("userId", this.$store.state.user.id);
        params.append("examId", this.$store.state.exam.nowExam.id);
        params.append("answer", this.answer);
        let {data} = await this.$axios.post("/isRight", params);
        if (data && data.isRight === '1') {
          this.$message.success('回答正确');
          this.$store.commit("exam/addRight")
        } else {
          this.$message.error('错了哦');
          this.$store.commit("exam/addError")
        }
        this.$store.commit("exam/addSub");
      }
    }
  }
</script>

<style scoped>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }

  .box-card {
    margin: 10px;
  }

  .fl-r {
    float: right;
  }
</style>
