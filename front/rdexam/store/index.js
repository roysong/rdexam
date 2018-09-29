export const state = () => ({
  user: null
})
export const mutations = {
  logoff (state){
    state.user = null
  }
}
export const actions = {
  nuxtServerInit ({ commit }, { req }) {
    if (req.session && req.session.user) {
      commit('user', req.session.user)
    }
  }
}
export const getters = {
  isLogin (state) {
    return !!state.user
  },
  loggedUser (state) {
    return state.user
  }
}
