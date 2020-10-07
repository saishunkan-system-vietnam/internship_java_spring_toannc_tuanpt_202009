<template>

<v-container style="
    padding-top: 0px;
">
  <v-row >
    <v-col cols="12" sm="2" style="padding: 5px">
      <v-card>
        <v-list style="background-color: rgb(176 185 191);">
          <v-list-item v-for="item in items" :key="item.title">
            <v-list-item-content >
              <router-link style="color:black"
                :to="{ path: '/DetailTournametSoccer/' + item.idTour }"
              >
                <v-list-item-title v-text="item.nameTour"></v-list-item-title
              ></router-link>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-card>
    </v-col>
    <v-col cols="12" sm="8" style="padding: 0px"
      ><router-view></router-view
    ></v-col>
    <v-col cols="12" sm="2" style="padding: 0px">Right</v-col>
  </v-row>
</v-container>
</template>
<script>
export default {
  data() {
    return {
      items: [],
    };
  },
  created() {
    this.$store
      .dispatch("tournament/getByType", "Football")
      .then((response) => {
        this.items = response.data;
      });
  },
};
</script>