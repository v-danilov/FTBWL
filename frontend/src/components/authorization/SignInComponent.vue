<template>
  <v-container>
    <v-layout row wrap justify-center>
      <v-flex xs10>
        <v-form
          v-model="formIsValid"
          @submit.prevent="onSubmit">
          <v-container>
            <v-layout>
              <v-flex xs12 md12 class="text-xs-center">
                <v-avatar
                  :tile="false"
                  :size="300"
                  color="grey lighten-4"
                >
                  <!--This is very strange. Doesn't work from root '/'-->
                  <img src="../../assets/ftbwlLogo.png" alt="logo">
                </v-avatar>
              </v-flex>
            </v-layout>
            <v-layout justify-center>
              <!--User name-->
              <v-flex xs12 md4>
                <v-text-field
                  v-model="userData.username"
                  label="User name or email"
                  :rules="checkNotEmpty"
                ></v-text-field>
              </v-flex>
            </v-layout>
            <v-layout justify-center>
              <!--Password-->
              <v-flex xs12 md4>
                <v-text-field
                  type="password"
                  v-model="userData.password"
                  label="Password"
                  :rules="checkNotEmpty"
                ></v-text-field>
              </v-flex>
            </v-layout>

            <!-- Submit button -->
            <v-layout justify-center>
              <v-flex xs12 md4>
                <v-btn
                  :disabled="!formIsValid"
                  type="submit"
                  round
                  large
                  color="primary">
                  Sign in
                </v-btn>
                <v-btn
                  :depressed="true"
                  :flat="true"
                  round
                  color="primary"
                  to="SignUp"
                  :replace="true">
                  Sign up
                </v-btn>
              </v-flex>
            </v-layout>

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
                color="red"
                flat
                @click="toastBox.isActive = false"
              >
                Close
              </v-btn>
            </v-snackbar>
          </v-container>
        </v-form>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {AuthorizationTextConstants} from './constants/AuthorizationFormConstants'
import {ACTIONS} from '../util/constants/ActionConstants'

export default {
  name: 'SignInComponent',
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
      const {username, password} = this.userData
      this.$store.dispatch(ACTIONS.AUTHORIZATION.SIGN_IN, {username, password}).then(() => {
        if (this.$store.getters.isAuthenticated) {
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
