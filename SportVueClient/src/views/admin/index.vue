<template>
  <v-app class="grey lighten-4">
    <v-row>
  
      <v-col cols="12" sm="12"> <router-view /></v-col>
    
    </v-row>
  </v-app>
</template>

<script>
export default {
  name: "Admin",
  components: {},
  data: () => ({
    //
  }),
};
</script>
