<template>
  <v-container class="pt-2">
    <v-row>
      <v-col cols="12" sm="2" style="padding: 5px" class="pr-2">
        <v-card>
          <v-list style="background-color: white">
            <v-subheader><b style="color: red">TOURNAMENTS</b></v-subheader>
            <v-list-item v-for="item in items" :key="item.title">
              <v-list-item-content>
                <a
                  :href="
                    $router.resolve({
                      path: '/DetailTournamentFootball/' + item.idTour,
                    }).href
                  "
                  style="color: black"
                >
                  <v-list-item-title v-text="item.nameTour"   v-b-popover.hover.top="item.nameTour"></v-list-item-title
                ></a>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
      <v-col cols="12" sm="8" style="padding: 0px" class="pt-1"
        ><router-view></router-view
      ></v-col>
      <v-col cols="12" sm="2" style="padding: 5px">
        <v-card>
          <v-list style="background-color: white">
            <v-subheader
              >
              <b style="color: green; min-width: 100px" class="text-center"
                > <a
                  :href="
                    $router.resolve({
                      path: '/RankTournament/Football'
                    }).href
                  "
                >RANK</a></b
              ></v-subheader
            >
            <v-list-item v-for="(item, index) in rank" :key="index">
              <v-list-item-content>

                <a
                  :href="
                    $router.resolve({
                      path: '/DetailTeamFootball/' + item.idTeam,
                    }).href
                  "
                  style="color: black"
                >
                  <div>
                    <v-row>
                      <b-col sm="1">
                        <div
                          :style="index==0?'width: 20px;height: 20px;background: #ee1d1d;border-radius: 60px;':index==1?'width: 20px;height: 20px;background: pink;border-radius: 60px;':''"
                          class="text-center"
                        >
                          {{ index + 1 }}
                        </div></b-col
                      >
                      <b-col sm="9">{{ item.name }}</b-col>
                    </v-row>
                  </div>
                </a>

              </v-list-item-content>
            </v-list-item>
          </v-list>
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
        .dispatch("tournament/getByType", "Football")
        .then((response) => {
          this.items = response.data;
        });
    },
    getRank() {
      this.$store
        .dispatch("tournament/getRank", "Football")
        .then((response) => {
          this.rank = response.data;
        });
    },
  },
};
</script>