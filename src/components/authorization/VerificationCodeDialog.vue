<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent width="500">
      <v-card>
        <v-card-title class="headline primary white--text" primary-title>
          Введите код подтверждения
        </v-card-title>
        <v-card-text>
          Для активации своей учётной записи, пожалуйста, введите код, который
          был отправлен вам на электронную почту <strong>{{ userData.email || "" }}</strong>.
          <v-row>
            <v-col>
              <v-text-field class="centered-input" outlined v-model.number="digitCode.firstDigit"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field class="centered-input" outlined v-model.number="digitCode.secondDigit"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field class="centered-input" outlined v-model.number="digitCode.thirdDigit"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field class="centered-input" outlined v-model.number="digitCode.fourthDigit"></v-text-field>
            </v-col>
            <v-col>
              <v-text-field class="centered-input" outlined v-model.number="digitCode.fifthDigit"></v-text-field>
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="accent" text @click="dialog = false">Отмена</v-btn>
          <v-btn color="secondary" text @click="sendVerificationCode">Подтвердить</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

export default {
  name: 'VerificationCodeDialog',
  data () {
    return {
      dialog: false,
      digitCode: {
        firstDigit: null,
        secondDigit: null,
        thirdDigit: null,
        fourthDigit: null,
        fifthDigit: null
      },
      userData: Object
    }
  },
  computed: {
    verificationCode () {
      return (
        this.digitCode.firstDigit * 10000 +
          this.digitCode.secondDigit * 1000 +
            this.digitCode.thirdDigit * 100 +
              this.digitCode.fourthDigit * 10 +
                this.digitCode.fifthDigit
      )
    }
  },
  methods: {
    openDialog (userData) {
      this.dialog = true
      this.userID = userData.userID
      this.userData = userData
    },
    sendVerificationCode () {
      this.$http.post(`/users/${this.userData.id}/verification`, this.verificationCode, { headers: {
        'Content-Type': 'application/json'
      }})
        .then(response => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.SUCCESS, text: 'Account confirmed.'})
          this.dialog = false
          this.$router.push('/login')
        })
        .catch(error => {
          this.$store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.SUCCESS, text: 'Cant confirm account.'})
          console.log(error)
        })
    }
  }
}
</script>

<style scoped>
.centered-input >>> input {
  text-align: center
}
</style>
