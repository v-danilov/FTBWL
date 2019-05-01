<template>
  <v-container>
    <v-layout row justify-center >
      <v-flex xs7>
        <v-form

          v-model="formIsValid"
          @submit.prevent="onSubmit">
          <v-container >
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

            <!--Human data block-->
            <v-layout justify-center>
              <!--Last name-->
              <v-flex xs12 md4>
                <v-text-field
                  v-model="userData.lastName"
                  :rules="nameRules"
                  label="Last name"
                  required
                ></v-text-field>
              </v-flex>
              <!--First name-->
              <v-flex xs12 md4>
                <v-text-field
                  v-model="userData.firstName"
                  :rules="nameRules"
                  label="First name"
                  required
                ></v-text-field>
              </v-flex>
              <!--City-->
              <v-flex xs12 md4>
                <v-combobox
                  v-model="selectedCity"
                  :search-input.sync="cityPrefix"
                  label="City"
                  item-text="name"
                  :items="cities"
                  :return-object="true"
                  required>
                </v-combobox>
              </v-flex>
            </v-layout>

            <!--User data block-->
            <v-layout justify-center>
              <!--Email-->
              <v-flex xs12 md4>
                <v-text-field
                  type="email"
                  v-model="userData.email"
                  :rules="emailRules"
                  label="E-mail"
                  required
                ></v-text-field>
              </v-flex>
              <!--User name-->
              <v-flex xs12 md4>
                <v-text-field
                  v-model="userData.username"
                  :rules="userNameRules"
                  label="User name"
                  required
                ></v-text-field>
              </v-flex>
              <!--User alias-->
              <v-flex xs12 md4>
                <v-text-field
                  v-model="userData.alias"
                  label="Alias"
                ></v-text-field>
              </v-flex>
            </v-layout>

            <!--Security data block-->
            <v-layout justify-center>
              <!--Password-->
              <v-flex xs12 md4>
                <v-text-field
                  type="password"
                  v-model="userData.password"
                  :rules="passwordRules"
                  label="Password"
                  required
                ></v-text-field>
              </v-flex>
              <!--Confirm password-->
              <v-flex xs12 md4>
                <v-text-field
                  :rules="confirmPasswordRules"
                  type="password"
                  label="Confirm password"
                  required
                ></v-text-field>
              </v-flex>
            </v-layout>

            <v-layout justify-center>
              <v-flex xs12 md4>
                <v-btn
                  :disabled="!formIsValid"
                  type="submit"
                  round
                  large
                  color="primary">
                  Sign up
                </v-btn>
              </v-flex>
                <v-flex xs12 md4>
                <v-btn
                  :depressed="true"
                  :flat="true"
                  round
                  color="primary"
                  to="signIn"
                  :replace="true">
                  Sign in
                </v-btn>
              </v-flex>
            </v-layout>

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
import {HTTPResponseStatusConstants} from '../util/constants/CommonConstants'
import _ from 'lodash'
import {END_POINTS} from '../util/constants/EndPointsConstants'

export default {
  name: 'SignUpComponent',
  data: () => ({
    formIsValid: false,
    cityPrefix: null,
    selectedCity: null,
    userData: {
      lastName: '',
      firstName: '',
      cityId: 0,
      email: '',
      username: '',
      alias: '',
      password: ''
    },
    cities: [],
    // Rules
    nameRules: [
      v => /^[a-zA-ZА-Яа-я]{3,16}$/.test(v) || AuthorizationTextConstants.NAME_NOT_VALID
    ],
    emailRules: [
      v => !!v || AuthorizationTextConstants.EMAIL_REQUIRED,
      v => /^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i.test(v) || AuthorizationTextConstants.EMAIL_NOT_VALID
    ],
    userNameRules: [
      v => !!v || AuthorizationTextConstants.USERNAME_REQUIRED,
      v => /^[a-zA-Z0-9_-]{3,16}$/.test(v) || AuthorizationTextConstants.USERNAME_NOT_VALID
    ],
    passwordRules: [
      v => !!v || AuthorizationTextConstants.PASSWORD_REQUIRED,
      v => /^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$/.test(v) ||
          AuthorizationTextConstants.PASSWORD_NOT_VALID
    ],
    confirmPasswordRules: [
      // TODO
    ],
    toastBox: {
      isActive: false,
      text: ''
    }
  }),
  watch: {
    // TODO метод срабатывает при загрузке формы - запрашивает по пустому префиксу
    cityPrefix: _.debounce(function (prefix) {
      this.searchCityByPrefix(prefix)
    }, 700),
    selectedCity (selectedCityObject) {
      this.userData.cityId = selectedCityObject.id
    }
  },
  methods: {
    searchCityByPrefix (prefix) {
      // TODO делать search() только если комбобокс не имеет вариантов по предложенному префиксу
      this.cities = []
      this.$http.get(END_POINTS.AUTHORIZATION.GET_CITIES_BY_PREFIX)
        .then(response => {
          console.log(response.data)
          let data = response.data.payload
          data.forEach(element => this.cities.push({id: element.cid, name: element.title}))
          console.log(this.cities)
        })
        .catch(e => {
          console.log(e)
        })
    },
    // TODO
    checkPasswords (password) {
      return this.userData.password === password
    },
    displayToastWithMessage (message) {
      this.toastBox.text = message
      this.toastBox.isActive = true
    },
    onSubmit () {
      this.$http.post(END_POINTS.AUTHORIZATION.SIGN_UP, this.userData)
        .then(response => {
          if (response.status === HTTPResponseStatusConstants.OK) {
            this.displayToastWithMessage('Successful sign up')
          } else {
            console.log(response)
            this.displayToastWithMessage(response.statusText)
          }
        })
        .catch(e => {
          this.displayToastWithMessage('Internal error. Please contact with administrator.')
          console.log(e)
        })
    }
  }
}
</script>
