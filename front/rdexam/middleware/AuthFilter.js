/**
 * 登录状态过滤器
 */
export default function ({ store, redirect }) {
    if(store.state.user)
        console.log('login suc:',store.state.user.userName)
    else
        redirect("/")
}
