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
        <h3 style="color: white">{{headerText}}</h3>
      </v-card-title>

      <v-card-text v-html="bodyText">
        {{bodyText}}
      </v-card-text>

      <v-divider></v-divider>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn v-if="isMultiAction"
               color="primary"
               rounded
               text
               @click="confirm">
          Подтвердить
        </v-btn>
        <v-btn
          color="accent"
          text
          rounded
          @click="cancel"
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
  props: {
    headerText: {
      type: String,
      required: true
    },
    bodyText: {
      type: String,
      required: true
    },
    isMultiAction: {
      type: Boolean,
      required: false
    }
  },
  data () {
    return {
      active: false
    }
  },
  methods: {
    pop () {
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
