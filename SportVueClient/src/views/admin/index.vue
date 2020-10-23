<template>
  <v-app class="grey lighten-4">
    <v-row>
      <v-col cols="12" sm="12"> <router-view /></v-col>
    </v-row>
    <v-overlay :value="overlay">
      <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
  </v-app>
</template>

<script>
import Api from "@/api/BaseApi";
export default {
  name: "Admin",
  components: {},
  data: () => ({
    overlay: false,
  }),
};
</script>
