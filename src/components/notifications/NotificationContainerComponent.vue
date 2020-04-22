<template>
    <div>
      <Notification
         v-for="notification in notifications"
         :key="notification.id"
         :notification="notification">
      </Notification>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import NotificationComponent from './NotificationComponent.vue'

export default {
  components: {
    Notification: NotificationComponent
  },
  computed: mapState('notifications', ['notifications'])
}
</script>

<style scoped>

</style>
