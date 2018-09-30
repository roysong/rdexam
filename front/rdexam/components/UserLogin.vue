<template>
    <div class="container">
        <h1 class="title">Enovell R&D Online Exam App</h1>
        <div class="loginForm">
        <el-input
          v-model="userName"
          placeholder="请输入用户名"
          size="large"
          autocomplete="on"
          autofocus="true"
          clearable>
        </el-input>    
        <el-input
          v-model="password"
          type="password"
          placeholder="请输入密码"
          size="large"
          clearable>
        </el-input>    
        <el-button 
        style="width:30%"        
        type="primary"
        @click="login"
        round>
                登录
        </el-button>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return {
            userName: '',
            password: ''
        }
    },
    methods: {
        async login(){
            if(!this.userName){
                this.$message('用户名不能为空！')
                return
            }
            if(!this.password){
                this.$message('密码不能为空！')
                return
            }
            let param = new URLSearchParams();
            param.append('userName',this.userName);
            param.append('password',this.password);
            let {data} = await this.$axios.post('/login',param);
            if(!data){
                this.$message('用户名或密码错误，请检查后重新输入！')
                return
            }
            this.$store.commit('logged',data)
            if(data.admin)
                this.$router.push({path: '/admin'})
            else
                this.$router.push({path: '/exam'})
        }
    }
}
</script>

<style>
.container{
    display: flex;
    flex-flow: column;
    justify-content: center;
    align-items: center;
}
.title
{
    height: 200px;
    line-height:200px;
    text-align:center;
    color: #203657;
    animation: shadowAni 8s forwards;
    -moz-animation: shadowAni 8s forwards;  /* Firefox */
    -webkit-animation: shadowAni 8s forwards; /* Safari 和 Chrome */
    -o-animation: shadowAni 8s forwards;  /* Opera */
}
@keyframes shadowAni
{
from {text-shadow: 5px 5px 5px white;}
to {text-shadow: 8px 5px 5px gray;}
}

@-moz-keyframes shadowAni /* Firefox */
{
from {text-shadow: 5px 5px 5px white;}
to {text-shadow: 8px 5px 5px gray;}
}

@-webkit-keyframes shadowAni /* Safari 和 Chrome */
{
from {text-shadow: 5px 5px 5px white;}
to {text-shadow: 8px 5px 5px gray;}
}

@-o-keyframes shadowAni /* Opera */
{
from {text-shadow: 5px 5px 5px white;}
to {text-shadow: 8px 5px 5px gray;}
}
.loginForm{
    display: flex;
    flex-flow: column;
    justify-content: space-around;
    align-items: center;
    width: 50%;
    height: 400px;
}
</style>
