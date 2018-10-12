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
      <el-button v-if="isNext" @click="nextOne" type="primary">下一题<i class="el-icon-arrow-right el-icon--right"></i>
      </el-button>
    </el-button-group>
  </el-card>
</template>

<script>
  export default {
    name: "ExamRegion",
    data() {
      return {
        answer: null,
        radioDisabled: false,
        msg: null
      }
    },
    computed: {
      isNext() {
        return this.$store.state.score.mouthSub < 50
      }
    },
    async created() {
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
          let params = new URLSearchParams();
          params.append("userName", this.$store.state.user.userName);
          this.$axios.post("/getExam", params).then(res => {
            if (res.data) {
              this.$store.commit("exam/updateExam", res.data);
              this.radioDisabled = false;
              this.answer = null;
              if (this.msg) {
                this.msg.close();
              }
            }
          });
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
          this.msg = this.$message({message: '回答正确', type: 'success', duration: 0});
          this.$store.commit("score/addRight")
        } else {
          this.msg = this.$message({message: '错了哦', type: 'error', duration: 0});
          this.$store.commit("score/addError")
        }
        this.$store.commit("score/addSub");
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
