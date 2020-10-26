<template>
  <v-container class="pt-2">
    <v-row>
      <v-col cols="12" sm="2" style="padding: 5px" class="pr-2">
        <v-card>
          <v-list style="background-color: white">
            <v-subheader><b style="color: red">TOURNAMENTS</b></v-subheader>
            <v-list-item v-for="(item,index) in items" :key="index">
              <v-list-item-content>
                <a
                  :href="
                    $router.resolve({
                      path: '/DetailTournamentBasketBall/' + item.idTour,
                    }).href
                  "
                  style="color: black"
                >
                  <v-list-item-title v-b-popover.hover.top="item.nameTour">
                    {{
                    item.nameTour.length &lt; 10
                      ? item.nameTour
                      : item.nameTour.slice(0, 15) + "..."
                    }}</v-list-item-title
                  ></a
                >
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
      <v-col cols="12" sm="8" style="padding: 0px" class="pt-1">
        <router-view></router-view>
      </v-col>
      <v-col cols="12" sm="2" style="padding: 5px">
        <v-card>
          <div>
            <v-list style="background-color: white">
              <v-subheader>
                <b style="color: green; min-width: 100px" class="text-center">
                  <a
                    :href="
                      $router.resolve({
                        path: '/RankTournament/BasketBall',
                      }).href
                    "
                    >RANK</a
                  ></b
                ></v-subheader
              >

              <v-list-item v-for="(item, index) in rank" :key="index">
                <v-list-item-content v-b-popover.hover.top="item.name">
                <a
                  :href="
                    $router.resolve({
                      path: '/DetailTeamBasketBall/' + item.idTeam,
                    }).href
                  "
                  style="color: black"
                >
                  {{ index + 1 }}.

                  {{
                    item.name.length < 10
                      ? item.name
                      : item.name.slice(0, 10) + "..."
                  }}

                </a>
              </v-list-item-content>
              </v-list-item>
            </v-list>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
      items: [],
      rank: [],
    };
  },
  created() {
    this.getTournament();
    this.getRank();
  },
  methods: {
    getTournament() {
      this.$store
        .dispatch("tournament/getByType", "Basketball")
        .then((response) => {
          this.items = response.data;
        });
    },
    getRank() {
      this.$store
        .dispatch("tournament/getRank", "Basketball")
        .then((response) => {
          this.rank = response.data;
          this.rank.splice(5, this.rank.length - 4);
        });
    },
  },
};
</script>