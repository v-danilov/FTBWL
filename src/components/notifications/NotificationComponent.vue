<template>
  <v-snackbar
    v-model="isActive"
    :multi-line="false"
    :right="true"
    :timeout="6000"
    :top="true"
    :color="notification.type.color"
  >
    <v-icon color="white" class="mr-2">{{ notification.type.icon }}</v-icon>
    {{notification.text}}
    <v-btn @click="isActive = false" text>Close</v-btn>
  </v-snackbar>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  props: {
    notification: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      isActive: true // is active by default. Visiblity managed by NotificationContainer
    }
  },
  beforeDestroy () {
    this.remove(this.notification)
  },
  methods: mapActions('notifications', ['remove'])
}
</script>

<style scoped></style>
