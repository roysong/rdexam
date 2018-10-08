<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span>用户信息</span>
      <el-button @click="logOut" style="float: right;" type="danger" size="small">退出登录</el-button>
    </div>
    <div class="text-center">
      <el-row :gutter="20">
        <el-col :span="4">姓名：{{userName}}</el-col>
        <el-col :span="4">累计答题：{{score.allSubmit}}</el-col>
        <el-col :span="4">累计正确：{{score.allRight}}</el-col>
        <el-col :span="4">累计错误：{{score.allError}}</el-col>
        <el-col :span="4">正确率：{{correctRate}}</el-col>
        <el-col :span="4">错误率：{{errorRate}}</el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="8">
          <el-card :body-style="{ padding: '0px' }" shadow="never" class="no-border">
            <el-progress type="circle" :percentage=mouthScore></el-progress>
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
    export default {
        name: "UserInfo",
      computed:{
        userName(){
          return this.$store.state.user?this.$store.state.user.userName:'未登录';
        },
        score(){
          console.log(this.$store.state)
          return this.$store.state.exam.score;
        },
        correctRate(){
          return (this.score.allRight/this.score.allSubmit*100).toFixed(2)+"%";
        },
        errorRate(){
          return (this.score.allError/this.score.allSubmit*100).toFixed(2)+"%";
        },
        mouthScore(){
          return this.score.mouthSub*2;
        },
        mouthRigthScore(){
          return this.score.mouthRight*2;
        },
        mouthErrorScore(){
          return this.score.mouthError*2;
        }
      },
      methods:{
        logOut(){
          this.$axios.get("/logout")
          this.$store.commit("logoff")
          this.$router.go(0)
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
