<template>
  <v-app class="grey lighten-4 fixHeight">
    <navbar class="header" />
    <v-main>
      <tab-home />
    </v-main>
  </v-app>
</template>

<script>
import Navbar from "@/views/web/Navbar";
import TabHome from "@/views/web/contents";

export default {
  name: "Web",
  components: { Navbar, TabHome },
  data: () => ({
    loading: 1,
    upcomingMatch: [],
    tournamet: "",
  }),
  methods: {
    home() {},
  },
  created() {
    this.$store.dispatch("schedule/upcomingMatch").then((response) => {
      this.upcomingMatch = response.data;
    });
    this.$store.dispatch("tournament/getAll").then((response) => {
      this.tournamet = response.data;
      // console.log(response.data);
    });
  },
  computed:{
    overlay(){
      return this.$store.state.auth.overlay;
    }
  }
};
</script>
<style>
.space {
  background-color: darkgray;
  height: 64px;
}
</style>
