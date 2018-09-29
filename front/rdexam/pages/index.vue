<template>
  <div>
    <user-login v-if="!isLogin"/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import UserLogin from '~/components/UserLogin'
console.log(UserLogin )
export default {
  computed: mapGetters(['isLogin','loggedUser']),
  components: {UserLogin}
}
</script>

<style>
</style>
