<!--答题界面用户信息-->
<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <el-button @click="isShowInfo" :icon="iconName" circle></el-button>
      <span>用户信息</span>
      <el-button @click="logOut" style="float: right;" type="danger" size="small">退出登录</el-button>
    </div>
    <div class="text-center" v-if="infoShow">
      <el-row :gutter="10">
        <el-col :md="4" :sm="6" :xs="24">姓名：{{userName}}</el-col>
        <el-col :md="4" :sm="6" :xs="12">累计完成：{{score.allSubmit}}</el-col>
        <el-col :md="4" :sm="6" :xs="12">累计正确：{{score.allRight}}</el-col>
        <el-col :md="4" :sm="6" :xs="12">累计错误：{{score.allError}}</el-col>
        <el-col :md="4" :sm="12" :xs="12">累计正确率：{{correctRate}}</el-col>
        <el-col :md="4" :sm="12" :xs="12">累计错误率：{{errorRate}}</el-col>
        <el-col :sm="8" :xs="12" class="hidden-md-and-up">当月完成:{{mouthScore}}%</el-col>
        <el-col :sm="8" :xs="12" class="hidden-md-and-up">正确:{{mouthRigthScore}}%</el-col>
        <el-col :sm="8" :xs="12" class="hidden-md-and-up">错误:{{mouthErrorScore}}%</el-col>
      </el-row>
      <el-row :gutter="20" class="hidden-sm-and-down">
        <el-col :span="8">
          <el-card :body-style="{ padding: '0px' }" shadow="never" class="no-border">
            <el-progress type="circle" :percentage=mouthScore :status="mouthScoreType"></el-progress>
            <div>当月完成</div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card :body-style="{ padding: '0px' }" shadow="never" class="no-border">
            <el-progress type="circle" :percentage=mouthRigthScore color="#67C23A"></el-progress>
            <div>正确</div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card :body-style="{ padding: '0px' }" shadow="never" class="no-border">
            <el-progress type="circle" :percentage=mouthErrorScore color="#F56C6C"></el-progress>
            <div>错误</div>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script>
  import 'element-ui/lib/theme-chalk/display.css';

  export default {
    name: "UserInfo",
    data() {
      return {
        infoShow: true,
      }
    },
    computed: {
      iconName() {
        return this.infoShow ? "el-icon-caret-top" : "el-icon-caret-bottom"
      },
      userName() {
        return this.$store.state.user ? this.$store.state.user.userName : '未登录';
      },
      score() {
        console.log(this.$store.state.score,'score')
        return this.$store.state.score;
      },
      correctRate() {
        let rate = this.score.allRight / this.score.allSubmit * 100;
        return rate ? rate.toFixed(2) + "%" : '0%';
      },
      errorRate() {
        let rate = this.score.allError / this.score.allSubmit * 100;
        return rate ? rate.toFixed(2) + "%" : '0%';
      },
      mouthScore() {
        return this.score.mouthSub * 2 >= 100 ? 100 : this.score.mouthSub * 2;
      },
      mouthRigthScore() {
        return this.score.mouthRight * 2 >= 100 ? 100 : this.score.mouthRight * 2;
      },
      mouthErrorScore() {
        return this.score.mouthError * 2 >= 100 ? 100 : this.score.mouthError * 2;
      },
      mouthScoreType() {
        return this.mouthScore === 100 ? 'success' : null;
      }
    },
    methods: {
      logOut() {
        this.$axios.get("/logout")
        this.$store.commit("logoff")
        this.$router.go(0)
      },
      isShowInfo() {
        this.infoShow = !this.infoShow
      }
    }
  }
</script>

<style scoped>
  .text-center {
    text-align: center;
  }

  .no-border {
    margin-top: 20px;
    border: none;
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
</style>
