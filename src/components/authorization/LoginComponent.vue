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
                  to="Register"
                >
                  Sign up
                </v-btn>
              </v-col>
            </v-row>

            <!-- Toast -->
            <v-snackbar
              v-model="toastBox.isActive"
              :multi-line="false"
              :right="true"
              :timeout="5000"
              :top="true"
            >
              {{ toastBox.text }}
              <v-btn
                @click="toastBox.isActive = false"
                color="red"
                text
              >
                Close
              </v-btn>
            </v-snackbar>
          </v-container>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { AuthorizationTextConstants } from './constants/AuthorizationFormConstants'
import { HTTPResponseStatusConstants } from '../util/constants/CommonConstants'
import { END_POINTS } from '../util/constants/EndPointsConstants'
import UserCookies from '../../store/cookie/UserCookiesClass'

export default {
  data () {
    return {
      formIsValid: false,
      userData: {
        username: '',
        password: ''
      },
      toastBox: {
        isActive: false,
        text: ''
      },
      // Rules
      checkNotEmpty: [
        v => !!v || AuthorizationTextConstants.FIELD_MUST_BE_NOT_EMPTY
      ]
    }
  },
  methods: {
    displayToastWithMessage (message) {
      this.toastBox.text = message
      this.toastBox.isActive = true
    },
    onSubmit () {
      const { username, password } = this.userData
      this.$http.post(END_POINTS.AUTHENTICATION.AUTHENTICATE, { username, password })
        .then(response => {
          if (response.status === HTTPResponseStatusConstants.OK) {
            UserCookies.setToken(response.data.token)
            this.$router.push('/')
          } else {
            this.displayToastWithMessage('Wrong credentials')
          }
        })
    }
  }
}
</script>

<style scoped>

</style>
