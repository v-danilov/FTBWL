<template>
  <!-- Round info card -->
  <v-card>
    <v-card-text>
      <!-- Round status -->
      <v-row no-gutters>
        <v-col>
          <span>Дата начала:</span>
        </v-col>
        <v-col>
          {{ round.timeStart }}
        </v-col>
        <v-col>
          <span>Дата окончания:</span>
        </v-col>
        <v-col>
          {{ round.timeEnd }}
        </v-col>
        <v-col>
          <span>Статус: </span>
        </v-col>
        <v-col>
          <span :style="{ color: statusStyleByCode(round.status.code).color }">
            {{ round.status.value }}
          </span>
        </v-col>
      </v-row>
      <!--Round parameters -->
      <v-row no-gutters>
        <v-col>
          <span>Расстановка:</span>
        </v-col>
        <v-col>{{
          round.schemePool
            ? round.schemePool.gameDeploy.systemName
            : "Не указано"
        }}</v-col>

        <v-col>
          <span>Стратегия:</span>
        </v-col>
        <v-col>{{
          round.schemePool
            ? round.schemePool.gameStrategy.systemName
            : "Не указано"
        }}</v-col>

        <v-col>
          <span>Стратегия:</span>
        </v-col>
        <v-col>Вы её не видите, а её и нет</v-col>
      </v-row>
      <v-row no-gutters>
        <v-col>
          <StatusManageButton
            :entity-key-word="{ humanName: 'раунд', systemName: 'round' }"
            :event-status="round.status.code"
            @round-status-changed="updateRoundStatus"
          />
        </v-col>
        <v-col>
          <v-btn
            @click="roundSettingDialog = !roundSettingDialog"
            rounded
            color="primary"
            class="white--text mt-2"
          >
            Паринги
          </v-btn>
          <!-- TODO bug with form closing need to manage this if -->
          <RoundSettings v-model="roundSettingDialog" v-if="roundSettingDialog" :round="round" />
        </v-col>
      </v-row>
    </v-card-text>
  </v-card>
</template>

<script>
import RoundSettingsComponent from '@/components/event/round/RoundSettingsComponent'
import statusStyleByCode from '@/components/util/statusStyleByCode'
import { EVENT_STATUS_CODE } from '@/components/util/constants/EventStatusNames'
import StatusManageButtonComponent from '@/components/event/StatusManageButtonComponent'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'

export default {
  components: {
    StatusManageButton: StatusManageButtonComponent,
    RoundSettings: RoundSettingsComponent
  },
  props: {
    round: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      roundSettingDialog: false
    }
  },
  methods: {
    updateRoundStatus (value) {
      this.$http
        .put(
          `/events/${this.$store.getters.currentActiveEventID}/rounds/${
            this.round.id
          }/status`,
          { code: value }
        )
        .then(response => {
          this.round.status = response.data.status
        })
        .catch(err => {
          this.$store.dispatch('notifications/add', {
            type: NOTIFICATION_TYPES.ERROR,
            text: 'Can not update round status'
          })
          console.log('Failed to refresh round. ' + err.message)
        })
    },
    statusStyleByCode (status) {
      return statusStyleByCode(status)
    }
  },
  computed: {
    eventStatusCodes () {
      return EVENT_STATUS_CODE
    }
  }
}
</script>

<style></style>
