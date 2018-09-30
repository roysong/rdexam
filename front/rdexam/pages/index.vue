<template>
  <div>
    <user-login v-if="!isLogin"/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import UserLogin from '~/components/UserLogin'
export default {
  computed: mapGetters(['isLogin','isAdmin']),
  components: {UserLogin}
}
</script>

<style>
</style>
