export const state = () => ({
  user: null
})
export const mutations = {
  logoff (state){
    state.user = null
  },
  logged (state,user) {
    state.user = user
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
  isAdmin (state) {
    return state.user && state.user.admin
  },
  loggedUser (state) {
    return state.user
  }
}
