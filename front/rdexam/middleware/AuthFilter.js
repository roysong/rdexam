/**
 * 登录状态过滤器
 */
export default function ({ store, redirect }) {
    if(!store.state.user)
        redirect("/")
}
