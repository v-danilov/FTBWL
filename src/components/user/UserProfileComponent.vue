<template>
    <div>
        This is {{userProfile.username}}'s profile.
    </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'UserProfileComponent',
  beforeRouteEnter (to, from, next) {
    next(vm => {
      axios.get('/user/profile').then(response => {
        console.log('api call')
        vm.setUserProfile(response.data)
      })
    })
  },
  data () {
    return {
      userProfile: Object
    }
  },
  methods: {
    setUserProfile (up) {
      this.userProfile = up
    }
  }
}
</script>

<style scoped>

</style>
