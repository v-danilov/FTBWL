<template>
  <v-row justify="center">
    <v-col cols="4">
      <v-banner>
        <v-avatar
          slot="icon"
          size="40"
        >
          <v-icon color="accent">
            warning
          </v-icon>
        </v-avatar>
        Произошла ошибка, повторите попытку позже.
      </v-banner>
    </v-col>
  </v-row>

</template>

<script>
export default {
  name: 'ErrorComponent'
}
</script>

<style scoped>

</style>
