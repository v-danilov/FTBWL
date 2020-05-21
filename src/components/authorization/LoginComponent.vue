<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="10">
        <v-form
          v-model="formIsValid"
          @submit.prevent="onSubmit"
        >
          <v-container>
            <v-row>
              <v-col
                cols="12"
                md="12"
                class="text-center"
              >
                <v-avatar
                  :tile="false"
                  :size="300"
                  color="grey lighten-4"
                >
                  <!--This is very strange. Doesn't work from root '/'-->
                  <img
                    src="../../assets/ftbwlLogo.png"
                    alt="logo"
                  >
                </v-avatar>
              </v-col>
            </v-row>
            <v-row justify="center">
              <!--User name-->
              <v-col
                cols="12"
                md="4"
              >
                <v-text-field
                  v-model="userData.username"
                  :rules="checkNotEmpty"
                  label="User name or email"
                />
              </v-col>
            </v-row>
            <v-row justify="center">
              <!--Password-->
              <v-col
                cols="12"
                md="4"
              >
                <v-text-field
                  v-model="userData.password"
                  :rules="checkNotEmpty"
                  type="password"
                  label="Password"
                />
              </v-col>
            </v-row>

            <!-- Submit button -->
            <v-row justify="center">
              <v-col
                cols="12"
                md="4"
              >
                <v-btn
                  :disabled="!formIsValid"
                  type="submit"
                  rounded
                  large
                  color="primary"
                >
                  Sign in
                </v-btn>
                <v-btn
                  :depressed="true"
                  :text="true"
                  :replace="true"
                  rounded
                  color="primary"
                  to="registration"
                >
                  Sign up
                </v-btn>
              </v-col>
            </v-row>
          </v-container>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { AuthorizationTextConstants } from './constants/AuthorizationFormConstants'
import { END_POINTS } from '../util/constants/EndPointsConstants'
import UserCookies from '../../store/cookie/UserCookiesClass'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

export default {
  data () {
    return {
      formIsValid: false,
      userData: {
        username: '',
        password: ''
      },
      // Rules
      checkNotEmpty: [
        v => !!v || AuthorizationTextConstants.FIELD_MUST_BE_NOT_EMPTY
      ]
    }
  },
  methods: {
    onSubmit () {
      const { username, password } = this.userData
      this.$http.post(END_POINTS.AUTHENTICATION.AUTHENTICATE, { username, password })
        .then(response => {
          // save user token
          UserCookies.setToken(response.data.token)
          // save user data
          this.setAuthenticatedUser()
          // check path to jump if previous user request aborted by invalid permissions
          const pathToJump = this.$store.getters.routeToJump
          console.log(pathToJump)
          this.$router.push(pathToJump)
        })
        .catch(err => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to authenticate.'})
          console.log(err)
        })
    },
    setAuthenticatedUser () {
      this.$http.get('/users/current')
        .then(response => {
          UserCookies.setAutheticatedUser(response.data)
        })
        .catch(error => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to authenticate. Cannot read user.'})
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>

</style>
