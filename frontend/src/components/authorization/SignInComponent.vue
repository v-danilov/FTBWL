<template>
  <v-form
    v-model="formIsValid"
    @submit.prevent="onSubmit">
    <v-container fluid>
      <v-avatar
        :tile="false"
        :size="300"
        color="grey lighten-4"
      >
        <!--This is very strange. Doesn't work from root '/'-->
        <img src="../../assets/ftbwlLogo.png" alt="logo">
      </v-avatar>
      <v-layout wrap>
        <v-flex xs12 md4></v-flex>
        <!--User name-->
        <v-flex xs12 md4>
          <v-text-field
            v-model="userData.username"
            label="User name or email"
            :rules="checkNotEmpty"
          ></v-text-field>
        </v-flex>
        </v-layout>
      <v-layout wrap>
        <v-flex xs12 md4></v-flex>
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
      <v-layout wrap>
        <v-flex xs12 md4></v-flex>
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
            to="signUp"
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
</template>

<script>
import {AuthorizationTextConstants} from './constants/AuthorizationFormConstants'

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
      this.$store.dispatch('SIGNIN_ACTION', {username, password}).then(() => {
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
