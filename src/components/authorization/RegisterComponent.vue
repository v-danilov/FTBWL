<template>
  <v-container>
    <v-row  justify="center" >
      <v-col cols="7">
        <v-form

          v-model="formIsValid"
          @submit.prevent="onSubmit">
          <v-container >
            <v-row>
              <v-col cols="12" md="12" class="text-center">
                <v-avatar
                  :tile="false"
                  :size="300"
                  color="grey lighten-4"
                >
                  <!--This is very strange. Doesn't work from root '/'-->
                  <img src="../../assets/ftbwlLogo.png" alt="logo">
                </v-avatar>
              </v-col>
            </v-row>

            <!--Human data block-->
            <v-row justify="center">
              <!--Last name-->
              <v-col cols="12" md="4">
                <v-text-field
                  v-model="userData.lastName"
                  :rules="nameRules"
                  label="Last name"
                  required
                ></v-text-field>
              </v-col>
              <!--First name-->
              <v-col cols="12" md="4">
                <v-text-field
                  v-model="userData.firstName"
                  :rules="nameRules"
                  label="First name"
                  required
                ></v-text-field>
              </v-col>
              <!--City-->
              <v-col cols="12" md="4">
                <v-combobox
                  v-model="selectedCity"
                  :search-input.sync="cityPrefix"
                  label="City"
                  item-text="name"
                  :items="cities"
                  :return-object="true"
                  required>
                </v-combobox>
              </v-col>
            </v-row>

            <!--User data block-->
            <v-row justify="center">
              <!--Email-->
              <v-col cols="12" md="4">
                <v-text-field
                  type="email"
                  v-model="userData.email"
                  :rules="emailRules"
                  label="E-mail"
                  required
                ></v-text-field>
              </v-col>
              <!--User name-->
              <v-col cols="12" md="4">
                <v-text-field
                  v-model="userData.username"
                  :rules="userNameRules"
                  label="User name"
                  required
                ></v-text-field>
              </v-col>
              <!--User alias-->
              <v-col cols="12" md="4">
                <v-text-field
                  v-model="userData.alias"
                  label="Alias"
                ></v-text-field>
              </v-col>
            </v-row>

            <!--Security data block-->
            <v-row justify="center">
              <!--Password-->
              <v-col cols="12" md="4">
                <v-text-field
                  type="password"
                  v-model="userData.password"
                  :rules="passwordRules"
                  label="Password"
                  required
                ></v-text-field>
              </v-col>
              <!--Confirm password-->
              <v-col cols="12" md="4">
                <v-text-field
                  :rules="confirmPasswordRules"
                  type="password"
                  label="Confirm password"
                  required
                ></v-text-field>
              </v-col>
            </v-row>

            <v-row justify="center">
              <v-col cols="12" md="4">
                <v-btn
                  :disabled="!formIsValid"
                  type="submit"
                  rounded
                  large
                  color="primary">
                  Sign up
                </v-btn>
              </v-col>
                <v-col cols="12" md="4">
                <v-btn
                  :depressed="true"
                  :text="true"
                  rounded
                  color="primary"
                  to="login"
                  :replace="true">
                  Sign in
                </v-btn>
              </v-col>
            </v-row>

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
                text
                @click="toastBox.isActive = false"
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
import {AuthorizationTextConstants} from './constants/AuthorizationFormConstants'
import {HTTPResponseStatusConstants} from '../util/constants/CommonConstants'
import _ from 'lodash'
import {END_POINTS} from '../util/constants/EndPointsConstants'

export default {
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
      this.$http.get(END_POINTS.AUTHENTICATION.GET_CITIES_BY_PREFIX)
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
      this.$http.post(END_POINTS.AUTHENTICATION.SIGN_UP, this.userData)
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
