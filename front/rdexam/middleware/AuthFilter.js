/**
 * 登录状态过滤器
 */
export default function ({ store, redirect }) {
    if(store.getters.isLogin)
        console.log(store.state.user)
    else
        redirect("/")
}
