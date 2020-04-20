<template>
  <v-dialog
    v-model="active"
    width="400"
  >
    <v-card>
      <v-card-title
        class="primary"
        primary-title
      >
        <h3 style="color: white">{{ headerText }}</h3>
      </v-card-title>

      <v-card-text v-html="bodyText">
        {{ bodyText }}
      </v-card-text>

      <v-divider />

      <v-card-actions>
        <v-spacer />
        <v-btn
          v-if="isMultiAction"
          @click="confirm"
          color="primary"
          rounded
          text
        >
          Подтвердить
        </v-btn>
        <v-btn
          @click="cancel"
          color="accent"
          text
          rounded
        >
          Отмена
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'ConfirmationDialogComponent',
  data () {
    return {
      active: false,
      headerText: '',
      bodyText: '',
      isMultiAction: true
    }
  },
  methods: {
    open (headerText, bodyText, canBeAccepted = true) {
      this.headerText = headerText
      this.bodyText = bodyText
      this.isMultiAction = canBeAccepted
      this.active = true
      return new Promise((resolve, reject) => {
        this.result = resolve
      })
    },
    cancel () {
      this.active = false
      this.result(false)
    },
    confirm () {
      this.active = false
      this.result(true)
    },
    result () {
      return false
    }
  }
}
</script>

<style scoped>

</style>
